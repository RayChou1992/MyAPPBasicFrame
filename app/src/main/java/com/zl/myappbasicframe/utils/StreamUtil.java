package com.zl.myappbasicframe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

    /**
     * 读取输入流的内容, 返回字符串
     * @param is
     * @return
     * @throws IOException
     */
    public static String streamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        String string = baos.toString();
        is.close();
        baos.close();
        return string;
    }
}
