package io.jeffchang.dogdemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Util to get useful units.
 */

public class ResourceUtil {

    public static int convertDpToPixels(Context context, int dp) {
        return Math.round(dp*(
                context.getResources().getDisplayMetrics().xdpi
                        / DisplayMetrics.DENSITY_DEFAULT));
    }
}
