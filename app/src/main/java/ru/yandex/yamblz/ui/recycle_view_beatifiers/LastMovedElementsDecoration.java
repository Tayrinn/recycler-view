package ru.yandex.yamblz.ui.recycle_view_beatifiers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import ru.yandex.yamblz.ui.fragments.ContentAdapter;

/**
 * Created by Volha on 06.08.2016.
 */

public class LastMovedElementsDecoration extends RecyclerView.ItemDecoration  {

    private Paint paintRed;
    private final int offset;

    public LastMovedElementsDecoration(Context context) {

        offset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics());

        paintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRed.setColor(Color.RED);
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics()));
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int firstItemDecorationPosition = ((ContentAdapter) parent.getAdapter()).movedFromItemPosition;
        int secondItemDecorationPosition = ((ContentAdapter) parent.getAdapter()).movedToItemPosition;

        for( int i = 0; i < parent.getChildCount(); i++ ){
            final View child = parent.getChildAt(i);
            int childPosition = parent.getChildAdapterPosition(child);
            if ( firstItemDecorationPosition > 0 &&
                    (childPosition == firstItemDecorationPosition || childPosition == secondItemDecorationPosition) )
                drawChest(c, layoutManager, child);
        }
    }

    private void drawChest(Canvas c, RecyclerView.LayoutManager layoutManager, View child) {
        Path path = new Path();
        path.moveTo(
                layoutManager.getDecoratedLeft(child) + offset,
                layoutManager.getDecoratedTop(child) + offset);
        path.lineTo(
                layoutManager.getDecoratedRight(child) - offset,
                layoutManager.getDecoratedBottom(child) - offset);
        path.moveTo(
                layoutManager.getDecoratedLeft(child) + offset,
                layoutManager.getDecoratedBottom(child) - offset);
        path.lineTo(
                layoutManager.getDecoratedRight(child) - offset,
                layoutManager.getDecoratedTop(child) + offset);
        c.drawPath(path, paintRed);
    }
}
