package com.udacity.tourguide.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by geovani on 24/10/16.
 */


public class Transformation {


    public static int dpToPx(int dp, Resources resources) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }


}
