package com.ramavathubalaji.lost_found;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by RAMAVATHU BALAJI on 30-06-2018.
 */

public class LoginViewPager extends ViewPager{
    private int duration;

    public LoginViewPager(Context context) {
        super(context);
        postInitViewPager();
    }

    public LoginViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    private void postInitViewPager() {
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            ScrollerCustomDuration mScroller = new ScrollerCustomDuration(getContext(),
                    new DecelerateInterpolator());
            scroller.set(this, mScroller);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public class ScrollerCustomDuration extends Scroller {

        public ScrollerCustomDuration(Context context) {
            super(context);
        }

        public ScrollerCustomDuration(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @SuppressLint("NewApi")
        public ScrollerCustomDuration(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duratio) {
            super.startScroll(startX, startY, dx, dy, duration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy,duration);
        }
    }
}
