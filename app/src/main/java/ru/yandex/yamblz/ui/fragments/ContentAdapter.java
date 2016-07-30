package ru.yandex.yamblz.ui.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.adapters.OnItemDragListener;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> implements OnItemDragListener {

    private final Random rnd = new Random();
    private List<Integer> colors = new ArrayList<>();
    public int movedFromItemPosition = -1;
    public int movedToItemPosition = -1;

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContentHolder holder = new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false));
        holder.itemView.setOnClickListener(v -> {
            int itemPosition = holder.getAdapterPosition();
            if ( itemPosition != RecyclerView.NO_POSITION ) {
                colors.set(itemPosition, Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
                notifyItemChanged(itemPosition);
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
        return position;
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
                if ( i >= 0 )
                    Collections.swap(colors, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                if ( i >= -1 )
                    Collections.swap(colors, i, i - 1);
            }
        }
        movedFromItemPosition = fromPosition;
        movedToItemPosition = toPosition;
        notifyItemMoved(fromPosition, toPosition);
    }

    static class ContentHolder extends RecyclerView.ViewHolder {
        ContentHolder(View itemView) {
            super(itemView);
//            itemView.setOnClickListener(v -> {
//                final Random rnd1 = new Random();
//                final int color = Color.rgb(rnd1.nextInt(255), rnd1.nextInt(255), rnd1.nextInt(255));
//                final ObjectAnimator animator = ObjectAnimator.ofObject((v), "backgroundColor", new ArgbEvaluator(), v.getSolidColor(), color);
//                animator.setDuration(500);
//                animator.start();
//            });
        }

        void bind(Integer color) {
            itemView.setBackgroundColor(color);
            ((TextView) itemView).setText("#".concat(Integer.toHexString(color).substring(2)));
        }
    }
}
