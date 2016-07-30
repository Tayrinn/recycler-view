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
import ru.yandex.yamblz.ui.adapters.TouchHelperCallback;

public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    GridLayoutManager glm;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu( true );
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        glm = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(glm);
        ContentAdapter adapter = new ContentAdapter();
        ItemTouchHelper.Callback touchCallback = new TouchHelperCallback( adapter );
        ItemTouchHelper touchHelper = new ItemTouchHelper( touchCallback );
        touchHelper.attachToRecyclerView( rv );
        rv.setAdapter(adapter);
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
                glm.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize( int position ) {
                        return 1;
                    }
                } );
                break;
            case R.id.menu2:
                glm.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize( int position ) {
                        return 2;
                    }
                } );
                break;
        }
        rv.invalidateItemDecorations();
        return super.onOptionsItemSelected( item );
    }
}
