package ru.yandex.yamblz.ui.adapters;

/**
 * Created by Volha on 28.07.2016.
 */
public interface OnItemDragListener {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
