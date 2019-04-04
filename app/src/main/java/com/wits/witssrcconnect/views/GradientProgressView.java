package com.wits.witssrcconnect.views;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class GradientProgressView extends View {
    public GradientProgressView(Context context) {
        super(context);
        startAnimation();
    }

    public GradientProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        startAnimation();
    }

    public GradientProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        startAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GradientProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        startAnimation();
    }

    private void startAnimation() {
        AnimationDrawable animationDrawable = (AnimationDrawable) getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(10000);
        animationDrawable.start();
    }
}
