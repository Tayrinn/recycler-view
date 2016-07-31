package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.recycle_view_beatifiers.FrameItemDecoration;
import ru.yandex.yamblz.ui.recycle_view_beatifiers.ListItemAnimator;
import ru.yandex.yamblz.ui.recycle_view_beatifiers.ScrollAnimationListener;
import ru.yandex.yamblz.ui.recycle_view_beatifiers.TouchHelperCallback;

public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    private GridLayoutManager glm;
    private int spanSize = 1;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu( true );
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        glm = new GridLayoutManager(getContext(), 30);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return spanSize;
            }
        });
        rv.setLayoutManager(glm);
        rv.addOnScrollListener(new ScrollAnimationListener());

        ContentAdapter adapter = new ContentAdapter();
        adapter.setHasStableIds(true);

        ItemTouchHelper.Callback touchCallback = new TouchHelperCallback( adapter );
        ItemTouchHelper touchHelper = new ItemTouchHelper( touchCallback );
        touchHelper.attachToRecyclerView( rv );

        FrameItemDecoration itemDecoration = new FrameItemDecoration();
        rv.addItemDecoration(itemDecoration);

        rv.setAdapter(adapter);
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
                spanSize = 30;
                break;
            case R.id.menu2:
                spanSize = 15;
                break;
            case R.id.menu3:
                spanSize = 10;
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
