package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;

import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.adapters.FrameItemDecoration;
import ru.yandex.yamblz.ui.adapters.ListItemAnimator;
import ru.yandex.yamblz.ui.adapters.TouchHelperCallback;

public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    GridLayoutManager glm;
    int spanSize = 12;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu( true );
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        glm = new GridLayoutManager(getContext(), 12);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return spanSize;
            }
        });
        rv.setLayoutManager(glm);
        ContentAdapter adapter = new ContentAdapter();
        adapter.setHasStableIds(true);
        ItemTouchHelper.Callback touchCallback = new TouchHelperCallback( adapter );
        ItemTouchHelper touchHelper = new ItemTouchHelper( touchCallback );
        FrameItemDecoration itemDecoration = new FrameItemDecoration();
        touchHelper.attachToRecyclerView( rv );
        rv.setAdapter(adapter);
        rv.addItemDecoration(itemDecoration);
        rv.setItemAnimator(new ListItemAnimator());
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater ) {
        super.onCreateOptionsMenu( menu, inflater );
        inflater.inflate( R.menu.main_menu, menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch ( item.getItemId() ) {
            case R.id.menu1:
                spanSize = 12;
                break;
            case R.id.menu2:
                spanSize = 6;
                break;
            case R.id.menu3:
                spanSize = 4;
                break;
            case R.id.menu4:
                spanSize = 3;
                break;
        }
        int firstVisible = glm.findFirstVisibleItemPosition();
        rv.getAdapter().notifyItemRangeChanged(firstVisible, 0);
        return super.onOptionsItemSelected( item );
    }
}
