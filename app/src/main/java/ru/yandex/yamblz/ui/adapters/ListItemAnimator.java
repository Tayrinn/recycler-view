package ru.yandex.yamblz.ui.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ru.yandex.yamblz.ui.fragments.ContentAdapter;

/**
 * Created by Volha on 31.07.2016.
 */

public class ListItemAnimator extends RecyclerView.ItemAnimator {
    @Override
    public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
        return false;
    }

    @Override
    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {

        return false;
    }

    @Override
    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
//        viewHolder.itemView.setAlpha(0);
//        viewHolder.itemView.animate()
//                    .alpha(1f)
//                    .rotationX(360f)
//                    .setDuration(300)
//                    .setListener(null);
        return false;
    }

    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        oldHolder.itemView.clearAnimation();
        newHolder.itemView.clearAnimation();
        oldHolder.itemView.animate().alpha(0f).setDuration(500).setListener(null);
        newHolder.itemView.setAlpha(0f);
        newHolder.itemView.animate().alpha(1f).setStartDelay(500).setDuration(500).setListener(null);

        return false;
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
