package com.dhiman.databinding.ui.fragments;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dhiman.databinding.utils.VersionUtils;

/**
 * Created by dhiman_da on 12/4/2015.
 */
public class BaseFragment extends Fragment {
    public BaseFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doRevealAnimation(view, 1000);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void doRevealAnimation(final View view, final long miliSeconds) {
        if (VersionUtils.isLollipop()) {
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);

                    final int width = view.getWidth();
                    final int height = view.getHeight();
                    final float hypotenuse = (float) Math.hypot(width, height);

                    final Animator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, hypotenuse);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(miliSeconds);
                    animator.start();

                    return false;
                }
            });
        }
    }
}
