package com.capton.common.base;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by capton on 2018/3/12.
 */

public class BannerPageTransformer implements ViewPager.PageTransformer {

    public static float MIN_ALPHA = 0.4F;
    public static float MAX_SCALE = 0.9F;

    public static float getMinAlpha() {
        return MIN_ALPHA;
    }

    public static void setMinAlpha(float minAlpha) {
        MIN_ALPHA = minAlpha;
    }

    public static float getMaxScale() {
        return MAX_SCALE;
    }

    public static void setMaxScale(float maxScale) {
        MAX_SCALE = maxScale;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {

        page.setAlpha(MIN_ALPHA);
        page.setScaleX(MAX_SCALE);
        page.setScaleY(MAX_SCALE);

        if(position >= -1 && position <= 1){
            if (position > 0) {
                if (1-position >= MIN_ALPHA)
                    page.setAlpha(1 - position);
                if (1-position >= MAX_SCALE) {
                    page.setScaleX(1 - position);
                    page.setScaleY(1 - position);
                }
            } else {
                if (1+position >= MIN_ALPHA)
                    page.setAlpha(1 + position);
                if (1+position >= MAX_SCALE) {
                    page.setScaleX(1 + position);
                    page.setScaleY(1 + position);
                }
            }
        }
    }
}
