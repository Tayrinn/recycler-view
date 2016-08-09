package ru.yandex.yamblz.ui.fragments;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.recycle_view_beatifiers.OnItemDragListener;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> implements OnItemDragListener {

    private final Random rnd = new Random();
    private final List<Integer> colors = new ArrayList<>();
    public int movedFromItemPosition = -1;
    public int movedToItemPosition = -1;

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ContentHolder holder = new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false));
        holder.itemView.setOnClickListener(v -> {
            int itemPosition = holder.getAdapterPosition();
            if ( itemPosition != RecyclerView.NO_POSITION ) {
                colors.set(itemPosition, Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
                notifyItemRangeChanged(itemPosition, 1);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ContentHolder holder, int position) {
        holder.bind(createColorForPosition(position));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public long getItemId(int position) {
        return createColorForPosition(position);
    }

    private Integer createColorForPosition(int position) {
        if (position >= colors.size()) {
            for ( int i = colors.size(); i <= position; ++i )
                colors.add(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        }
        return colors.get(position);
    }

    @Override
    public void onItemDismiss(int position) {
        colors.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(colors, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(colors, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);

        movedFromItemPosition = fromPosition;
        movedToItemPosition = toPosition;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public static class ContentHolder extends RecyclerView.ViewHolder {
        ContentHolder(View itemView) {
            super(itemView);
        }

        void bind(Integer color) {
            itemView.setBackgroundColor(color);
            ((TextView) itemView).setText("#".concat(Integer.toHexString(color).substring(2)));
        }
    }
}
