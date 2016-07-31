package ru.yandex.yamblz.ui.adapters;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import ru.yandex.yamblz.ui.fragments.ContentAdapter;

/**
 * Created by Volha on 28.07.2016.
 */
public class TouchHelperCallback extends ItemTouchHelper.Callback {

    private final ContentAdapter mAdapter;
    private Paint backgroundPaint;

    public TouchHelperCallback(ContentAdapter adapter) {
        mAdapter = adapter;
        backgroundPaint = new Paint();
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags( RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped( RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if ( actionState == ItemTouchHelper.ACTION_STATE_SWIPE ) {

            float itemWidth = viewHolder.itemView.getWidth();
            float transparency = Math.abs(dX) / itemWidth * 255;
            backgroundPaint.setColor(Color.argb((int) transparency, 255, 0, 0));

        } else {
            backgroundPaint.setColor(Color.argb(255, 255, 255, 255));
        }
        c.drawRect(0, 0, c.getWidth(), c.getHeight(), backgroundPaint);

    }
}
