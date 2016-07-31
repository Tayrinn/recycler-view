package ru.yandex.yamblz.ui.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Volha on 31.07.2016.
 */

public class ScrollAnimationListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        GridLayoutManager glm = (GridLayoutManager) recyclerView.getLayoutManager();

        int firstVisibleItemPosition = glm.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = glm.findLastVisibleItemPosition();
        int rowItemCount = glm.getSpanCount() / glm.getSpanSizeLookup().getSpanSize(firstVisibleItemPosition);

        if ( dy < 0 ) {
            animateRow(
                    recyclerView,
                    firstVisibleItemPosition,
                    firstVisibleItemPosition + rowItemCount);
        } else {
            animateRow(
                    recyclerView,
                    lastVisibleItemPosition - rowItemCount + 1,
                    lastVisibleItemPosition + 1);
        }
    }

    private void animateRow(RecyclerView recyclerView, int from, int to) {
        for ( int i = from; i < to; i++) {
            recyclerView.findViewHolderForAdapterPosition(i).itemView
                    .animate()
                    .rotationX(360)
                    .setDuration(500)
                    .start();
        }
    }
}
