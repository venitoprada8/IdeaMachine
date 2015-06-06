package hr.matvidako.ideamachine.base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.Optional;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.db.Data;

public abstract class BaseDataListActivity<T extends Data> extends MenuActivity implements BaseDataAdapter.OnItemClickListener, View.OnClickListener {

    @InjectView(R.id.empty_list)
    protected TextView emptyIdeaList;
    @Optional @InjectView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @InjectView(R.id.list)
    protected RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isUsingFab()) {
            setupFab();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListView();
        refreshAdapter();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab) {
            onFabClick();
        }
    }

    @Override
    protected int getMenuResId() {
        return 0;
    }

    private void setupListView() {
        //emptyIdeaList.setText(getEmptyViewStringResId());
        //listView.setEmptyView(emptyIdeaList);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(getAdapter());
        getAdapter().setOnItemClickListener(this);
        listView.setItemAnimator(new DefaultItemAnimator());
    }

    private void refreshAdapter() {
        getAdapter().refresh();
    }

    private void setupFab() {
        floatingActionButton.setOnClickListener(this);
    }

    protected abstract BaseDataAdapter<T> getAdapter();

    protected abstract boolean isUsingFab();

    protected abstract void onFabClick();

    protected abstract int getEmptyViewStringResId();

}
