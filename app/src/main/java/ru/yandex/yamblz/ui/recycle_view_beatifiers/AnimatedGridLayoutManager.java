package ru.yandex.yamblz.ui.recycle_view_beatifiers;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Volha on 06.08.2016.
 */

public class AnimatedGridLayoutManager extends GridLayoutManager {

    public AnimatedGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

        final int firstVisibleItemPosition = findFirstCompletelyVisibleItemPosition();
        final int lastVisibleItemPosition = findLastCompletelyVisibleItemPosition();
        final int rowItemCount = getSpanCount();// / getSpanSizeLookup().getSpanSize(firstVisibleItemPosition);
        if ( dy < 0 ) {
            animateRow(
                    firstVisibleItemPosition,
                    firstVisibleItemPosition + rowItemCount);
        } else {
            animateRow(
                    lastVisibleItemPosition - rowItemCount + 1,
                    lastVisibleItemPosition + 1);
        }
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    private void animateRow(int from, int to) {

        for ( int i = from; i < to; i++) {
            findViewByPosition(i)
                    .animate()
                    .rotationX(360)
                    .setDuration(500)
                    .start();
        }
    }

    @Override
    public void removeAndRecycleView(View child, RecyclerView.Recycler recycler) {
        if ( child != null && child.getAnimation() != null )
            child.getAnimation().cancel();
        super.removeAndRecycleView(child, recycler);
    }

    @Override
    public void removeAndRecycleViewAt(int index, RecyclerView.Recycler recycler) {
        View childView = findViewByPosition(index);
        if ( childView != null && childView.getAnimation() != null )
            findViewByPosition(index).getAnimation().cancel();
        super.removeAndRecycleViewAt(index, recycler);
    }
}
