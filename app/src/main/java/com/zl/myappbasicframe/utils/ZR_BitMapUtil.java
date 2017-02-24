package com.zl.myappbasicframe.utils;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 图片压缩
 * @author peng
 *
 */
public class ZR_BitMapUtil
{
	 private static final String TAG = "Car_OnLine_BitMapUtil";

	 /**
		 * 根据路径获得突破并压缩返回bitmap用于显示
		 * @return
		 */
		public static Bitmap getSmallBitmap(Boolean isLanscape, String filePath) {
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, options);

			// Calculate inSampleSize
			if (isLanscape) {
				
				options.inSampleSize = calculateInSampleSize(options, 640, 400);
			}else {
				options.inSampleSize = calculateInSampleSize(options, 400, 640);
				
			}

			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;

			return BitmapFactory.decodeFile(filePath, options);
		}
	 
	/**
     * @description 计算图片的压缩比率
     *
     * @param options 参数
     * @param reqWidth 目标的宽度
     * @param reqHeight 目标的高度
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * @description 通过传入的bitmap，进行压缩，得到符合标准的bitmap
     *
     * @param src
     * @param dstWidth
     * @param dstHeight
     * @return
     */
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight, int inSampleSize) {
    	// 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响，我们这里是缩小图片，所以直接设置为false
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) { // 如果没有缩放，那么不回收
            src.recycle(); // 释放Bitmap的native像素数组
        }
        return dst;
    }

    /**
     * @description 从Resources中加载图片
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置成了true,不占用内存，只获取bitmap宽高
        BitmapFactory.decodeResource(res, resId, options); // 读取图片长宽，目的是得到图片的宽高
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight); // 调用上面定义的方法计算inSampleSize值
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(res, resId, options); // 载入一个稍大的缩略图
        return createScaleBitmap(src, reqWidth, reqHeight, options.inSampleSize); // 通过得到的bitmap，进一步得到目标大小的缩略图
    }

    /**
     * 
     * @param data
     * @param reqWidth
     * @param reqHeight
     * @return 从字节中读取图片并压缩
     */
    public static Bitmap decodeBitmapFromByteArray(byte[] data, int reqWidth, int reqHeight)
    {
    	final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight); // 调用上面定义的方法计算inSampleSize值
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeByteArray(data, 0, data.length);
//        Bitmap src = BitmapFactory.decodeResource(res, resId, options); // 载入一个稍大的缩略图
        return createScaleBitmap(src, reqWidth, reqHeight, options.inSampleSize); // 通过得到的bitmap，进一步得到目标大小的缩略图
    }
    /**
     * @description 从SD卡上加载图片
     *
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
    	 
    	final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
       
        return createScaleBitmap(src, reqWidth, reqHeight, options.inSampleSize);
    }
    /**
     * 本地SD卡上的图片转成base64编码
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static String BitmapToBase64(String pathName, int reqWidth, int reqHeight)
    {
 
    	
    	final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
       ;
        
		return BitmapToBase64( createScaleBitmap(src, reqWidth, reqHeight, options.inSampleSize));
    	
    }
    
    /**
     * 压缩有转成base64
     * @param image
     * @return
     */
    private static String BitmapToBase64(Bitmap image) {
		byte[] imagedate = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		imagedate = baos.toByteArray();
		return Base64.encodeToString(imagedate, 0, imagedate.length,
				Base64.DEFAULT);
	}
    public  static Bitmap comp(Bitmap image) {

    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    	if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
    	baos.reset();//重置baos即清空baos
    	image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
    	}
    	ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
    	BitmapFactory.Options newOpts = new BitmapFactory.Options();
    	//开始读入图片，此时把options.inJustDecodeBounds 设回true了
    	newOpts.inJustDecodeBounds = true;
    	Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
    	newOpts.inJustDecodeBounds = false;
    	int w = newOpts.outWidth;
    	int h = newOpts.outHeight;
    	//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
    	float hh = 800f;//这里设置高度为800f
    	float ww = 480f;//这里设置宽度为480f
    	//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
    	int be = 1;//be=1表示不缩放
    	if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
    	be = (int) (newOpts.outWidth / ww);
    	} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
    	be = (int) (newOpts.outHeight / hh);
    	}
    	if (be <= 0)
    	be = 1;
    	newOpts.inSampleSize = be;//设置缩放比例
    	//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
    	isBm = new ByteArrayInputStream(baos.toByteArray());
    	bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
    	return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    	}
    public static Bitmap compressImage(Bitmap image) {

    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
    	int options = 100;
    	while ( baos.toByteArray().length / 1024>100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
    	baos.reset();//重置baos即清空baos
    	image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
    	options -= 10;//每次都减少10
    	}
    	ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
    	Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
    	return bitmap;
    	}
}
