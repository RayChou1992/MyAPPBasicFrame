package com.zl.myappbasicframe.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ray on 2016-09-08.
 */
public class FontHelper {
    /**
     * Apply specified font for all text views (including nested ones) in the specified
     * root view.
     *
     * @param context
     *            Context to fetch font asset.
     * @param root
     *            Root view that should have specified font for all it's nested text
     *            views.
     * @param fontPath
     *            Font path related to the assets folder (e.g. "fonts/YourFontName.ttf").
     */
    public static void applyFont(final Context context, final View root, final String fontPath) {
        try {
            if (root instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) root;
                for (int i = 0; i < viewGroup.getChildCount(); i++)
                    applyFont(context, viewGroup.getChildAt(i), fontPath);
            } else if (root instanceof TextView)
                ((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(), fontPath));
        } catch (Exception e) {
            Log.e("tag", String.format("Error occured when trying to apply %s font for %s view", fontPath, root));
            e.printStackTrace();
        }
    }
}
