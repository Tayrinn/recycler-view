package ru.yandex.yamblz.ui.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.yandex.yamblz.ui.fragments.ContentAdapter;

/**
 * Created by Volha on 31.07.2016.
 */

public class ListItemAnimator extends SimpleItemAnimator {

    private final int HALF_ANIMATION_DURATION = 500;
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        return false;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        ObjectAnimator oldObjectAnimator = ObjectAnimator.ofFloat(oldHolder.itemView, View.ALPHA, 1, 0f);
        oldObjectAnimator.setDuration(HALF_ANIMATION_DURATION);
        oldObjectAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                oldHolder.itemView.setAlpha(1f);
                newHolder.itemView.setAlpha(0f);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dispatchAnimationFinished(oldHolder);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                newHolder.itemView.setAlpha(1f);
            }
        });

        ObjectAnimator newObjectAnimator = ObjectAnimator.ofFloat(newHolder.itemView, View.ALPHA, 0, 1f);
        newObjectAnimator.setDuration(HALF_ANIMATION_DURATION);
        newObjectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dispatchAnimationFinished(newHolder);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                newHolder.itemView.setAlpha(1f);
            }
        });

        AnimatorSet bgAnim = new AnimatorSet();
        bgAnim.playSequentially(oldObjectAnimator, newObjectAnimator);
        bgAnim.start();
        return false;
    }

    @Override
    public void onAnimationFinished(RecyclerView.ViewHolder viewHolder) {
        super.onAnimationFinished(viewHolder);
        viewHolder.itemView.setAlpha(1f);
    }

    @Override
    public void runPendingAnimations() {
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        return false;
    }
}
