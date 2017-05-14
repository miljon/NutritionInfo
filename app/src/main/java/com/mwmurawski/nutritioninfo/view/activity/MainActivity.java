package com.mwmurawski.nutritioninfo.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerMainActivityComponent;
import com.mwmurawski.nutritioninfo.presenter.component.MainActivityComponent;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.ItemAdapterInterface;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityView, ItemAdapterInterface {

    @BindView(R.id.floating_search_view)    FloatingSearchView   searchView;
    @BindView(R.id.main_recyclerview)       RecyclerView         recyclerView;
    @BindView(R.id.progress_bar)            ProgressBar          progressBar;
    @BindView(R.id.swipe_refresh_layout)    SwipeRefreshLayout   swipeRefreshLayout;

    @Inject ItemAdapterInterface itemAdapter;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable(); //todo make an injection and move to presenter

    @Override
    protected int getLayoutFile() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        presenterProviderInterface = DaggerMainActivityComponent.builder().applicationComponent(getApplicationComponent()).build();
        ((MainActivityComponent)presenterProviderInterface).inject(this);
    }

    @Override
    public void asignPresenterValuesToViewAfterRestore() {
        itemAdapter.setData(presenter.getItemList());
        searchView.setSearchText(presenter.getQueryString());
    }

    /*
    ________LIFE CYCLE METHODS________
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.bindView(this);

        //bindView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter((RecyclerView.Adapter) itemAdapter);

        setSearchListener();
        setSwipeSearchListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

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
        compositeDisposable.clear();
    }

    /*
    ________ CUSTOM METHODS ________
     */

    private void setSearchListener() {
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {}

            @Override
            public void onFocusCleared() {
                presenter.loadResponse();
            }
        });

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                presenter.setQueryString(newQuery);
            }
        });
    }


    private void setSwipeSearchListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshList();
            }
        });
    }

    /*
    ________PRESENTER METHODS________
     */

    @Override
    public void setSwipeRefreshingToFalse(){
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void makeToast(String toastText) {
        Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void putListToAdapter(List<SearchItem> searchItems) {
        itemAdapter.setData(searchItems);
        recyclerView.setAdapter((RecyclerView.Adapter) itemAdapter);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setData(List<SearchItem> listOfItems) {

    }

    @Override
    public void initLayout() {

    }
}
