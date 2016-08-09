package ru.yandex.yamblz.ui.recycle_view_beatifiers;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import ru.yandex.yamblz.ui.fragments.ContentAdapter;

/**
 * Created by Volha on 30.07.2016.
 */

public class FrameItemDecoration extends RecyclerView.ItemDecoration {

    private final int offset;
    private Paint paintBlue, paintRed;

    public FrameItemDecoration(Context context) {

        offset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics());

        paintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.STROKE);
        paintBlue.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics()));

        paintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRed.setColor(Color.RED);
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics()));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = offset ;
        outRect.right = offset;
        outRect.top = offset;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for( int i = 0; i < parent.getChildCount(); i++ ){
            final View child = parent.getChildAt(i);
            int childPosition = parent.getChildAdapterPosition(child);
            if ( childPosition % 3 == 0 ) {
                drawRect(c, layoutManager, child);
            } else if ( childPosition % 3 == 1 ) {
                drawOval(c, layoutManager, child);
            }
        }
    }

    private void drawOval(Canvas c, RecyclerView.LayoutManager layoutManager, View child) {
        RectF rect = new RectF();
        rect.set(layoutManager.getDecoratedLeft(child) + offset,
                layoutManager.getDecoratedTop(child) + offset,
                layoutManager.getDecoratedRight(child) - offset,
                layoutManager.getDecoratedBottom(child));
        c.drawOval(rect, paintBlue);
    }

    private void drawRect(Canvas c, RecyclerView.LayoutManager layoutManager, View child) {
        c.drawRect(
                layoutManager.getDecoratedLeft(child) + offset,
                layoutManager.getDecoratedTop(child) + offset,
                layoutManager.getDecoratedRight(child) - offset,
                layoutManager.getDecoratedBottom(child),
                paintRed);
    }


}
