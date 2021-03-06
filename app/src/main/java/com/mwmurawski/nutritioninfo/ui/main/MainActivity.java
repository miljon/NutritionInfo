package com.mwmurawski.nutritioninfo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.ui.base.BaseActivity;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsActivity;
import com.mwmurawski.nutritioninfo.ui.main.adapter.ItemAdapterView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.floating_search_view)    FloatingSearchView   searchView;
    @BindView(R.id.main_recyclerview)       RecyclerView         recyclerView;
    @BindView(R.id.progress_bar)            ProgressBar          progressBar;
    @BindView(R.id.swipe_refresh_layout)    SwipeRefreshLayout   swipeRefreshLayout;

    @Inject ItemAdapterView itemAdapter;

    @Override
    protected int getLayoutFile() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void injectPresenter() {
        setPresenter(getActivityComponent().getMainPresenter());
    }

    @Override
    public void assignPresenterValuesToViewAfterRestore() {
        itemAdapter.setPresenter(presenter);
        itemAdapter.setData(presenter.getItemList());
        searchView.setSearchText(presenter.getQueryString());
    }

    @Override
    public void startDetailsActivity(final String ndbno) {
        Intent intent = new Intent(this, FoodDetailsActivity.class);
        intent.putExtra("ndbno", ndbno);
        startActivity(intent);
    }

    @Override
    public void putListToAdapter(List<SearchItem> searchItems) {
        itemAdapter.setData(searchItems);
        recyclerView.setAdapter((RecyclerView.Adapter) itemAdapter);
    }

    @Override
    public void showProgressBar() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        setSearchListener();
        setSwipeSearchListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.bindView(this);
        recyclerView.setAdapter((RecyclerView.Adapter) itemAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //onRestart

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Sets listener onFocusCleared, when search bar lost focus, takes text and do call to API.
     * Also sets listener onSearchTextChanged to always have latest value of text for use in API call.
     */
    private void setSearchListener() {
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {}

            @Override
            public void onFocusCleared() {
                presenter.loadSearchResponse();
            }
        });

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                presenter.setQueryString(newQuery);
            }
        });
    }

    /**
     * Sets down swipe listener for refresh results. Makes new API call when user refresh list.
     */
    private void setSwipeSearchListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadSearchResponse();
            }
        });
    }
}
