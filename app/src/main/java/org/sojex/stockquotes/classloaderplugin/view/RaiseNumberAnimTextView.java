package org.sojex.stockquotes.classloaderplugin.view;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

/**
 * RaiseNumberAnimTextView 有浮动动画的TextView
 * author:张冠之
 * time: 2017/8/22 上午11:22
 * e-mail: guanzhi.zhang@sojex.cn
 */

public class RaiseNumberAnimTextView extends AppCompatTextView {
    private long mDuration = 1000;
    private ValueAnimator mAnimator;
    private TimeInterpolator mTimeInterpolator = new LinearInterpolator();

    public RaiseNumberAnimTextView(Context context) {
        super(context);
    }

    public RaiseNumberAnimTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RaiseNumberAnimTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public void setTimeInterpolator(TimeInterpolator interpolator) {
        mTimeInterpolator = interpolator;
    }

    public void setAnimatorText(int num) {
        mAnimator = ValueAnimator.ofInt(0, num);
        mAnimator.setDuration(mDuration);
        mAnimator.setInterpolator(mTimeInterpolator);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        mAnimator.start();
    }
}

