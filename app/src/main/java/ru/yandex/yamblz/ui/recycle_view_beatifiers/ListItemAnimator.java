package ru.yandex.yamblz.ui.recycle_view_beatifiers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;

/**
 * Created by Volha on 31.07.2016.
 */

public class ListItemAnimator extends DefaultItemAnimator {

    private static final int HALF_ANIMATION_DURATION = 500;

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {

        if ( oldHolder.getAdapterPosition() != newHolder.getAdapterPosition() ) {
            return super.animateChange(oldHolder, newHolder, fromLeft, fromTop, toLeft, toTop);
        }

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
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        return true;
    }
}
