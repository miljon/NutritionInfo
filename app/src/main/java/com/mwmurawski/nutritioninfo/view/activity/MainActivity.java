package com.mwmurawski.nutritioninfo.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerMainActivityPresenterComponent;
import com.mwmurawski.nutritioninfo.presenter.module.MainActivityPresenterModule;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.ItemAdapterInterface;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements MainActivityView, ItemAdapterInterface {

    @BindView(R.id.floating_search_view)    FloatingSearchView   searchView;
    @BindView(R.id.main_recyclerview)       RecyclerView         recyclerView;
    @BindView(R.id.progress_bar)            ProgressBar          progressBar;
    @BindView(R.id.swipe_refresh_layout)    SwipeRefreshLayout   swipeRefreshLayout;

    @Inject MainActivityPresenter presenter;
    @Inject ItemAdapterInterface itemAdapter;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    /*
    ________LIFE CYCLE METHODS________
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainActivityPresenterComponent.builder().mainActivityPresenterModule(new MainActivityPresenterModule(this)).build().inject(this);

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
        if(!presenter.isAttachedToView()) presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
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
}
