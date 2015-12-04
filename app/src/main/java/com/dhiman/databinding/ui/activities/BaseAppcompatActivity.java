package com.dhiman.databinding.ui.activities;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.dhiman.databinding.R;
import com.dhiman.databinding.utils.AnimatorUtils;
import com.dhiman.databinding.utils.VersionUtils;

/**
 * Created by dhiman_da on 12/2/2015.
 */
public class BaseAppcompatActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (VersionUtils.isLollipop()) {
            // set an enter transition
            //getWindow().setEnterTransition(new Explode());
            // set an exit transition
            //getWindow().setExitTransition(new Explode());
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    public void setupToolbarWithText(final Toolbar toolbar, final String text) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_text_view);
            if (textView != null) {
                textView.setText(text);
                doRevealAnimation(textView, AnimatorUtils.LONG_ANIMATION_DURATION);
            }
        }
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
