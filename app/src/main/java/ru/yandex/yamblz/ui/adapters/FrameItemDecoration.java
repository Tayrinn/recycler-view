package ru.yandex.yamblz.ui.adapters;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.yandex.yamblz.R;

/**
 * Created by Volha on 30.07.2016.
 */

public class FrameItemDecoration extends RecyclerView.ItemDecoration {

    private final int offset = 15;
    private Paint paintBlue, paintRed;

    public FrameItemDecoration() {
        paintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.STROKE);
        paintBlue.setStrokeWidth(3);

        paintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRed.setColor(Color.RED);
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(4);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = offset;
        outRect.right = offset;
        outRect.bottom = offset;
        outRect.top = offset;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for( int i = 0; i < parent.getChildCount(); i++ ){
            final View child = parent.getChildAt(i);
            if ( parent.getChildAdapterPosition(child) % 3 == 0 ) {
                drawRect(c, layoutManager, child);
            } else if ( parent.getChildAdapterPosition(child) % 3 == 1 ) {
                drawOval(c, layoutManager, child);
            }
        }
    }

    private void drawOval(Canvas c, RecyclerView.LayoutManager layoutManager, View child) {
        RectF rect = new RectF(layoutManager.getDecoratedLeft(child) + offset,
                layoutManager.getDecoratedTop(child) + offset,
                layoutManager.getDecoratedRight(child) - offset,
                layoutManager.getDecoratedBottom(child) - offset);
        c.drawOval(rect, paintBlue);
    }

    private void drawRect(Canvas c, RecyclerView.LayoutManager layoutManager, View child) {
        c.drawRect(
                layoutManager.getDecoratedLeft(child) + offset,
                layoutManager.getDecoratedTop(child) + offset,
                layoutManager.getDecoratedRight(child) - offset,
                layoutManager.getDecoratedBottom(child) - offset,
                paintRed);
    }


}
