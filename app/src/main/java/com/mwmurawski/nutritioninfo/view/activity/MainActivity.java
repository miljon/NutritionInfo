package com.mwmurawski.nutritioninfo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.MainActivityView;
import com.mwmurawski.nutritioninfo.view.recyclerview.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    @BindView(R.id.floating_search_view) FloatingSearchView searchView;
    @BindView(R.id.main_recyclerview) RecyclerView recyclerView;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MainActivityPresenter presenter;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainActivityPresenter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(new ArrayList<>());
        recyclerView.setAdapter(itemAdapter);

        setSearchListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!presenter.isAttachedToView()) presenter.onAttachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onDetachView();
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

    private void setSearchListener() {
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {}

            @Override
            public void onFocusCleared() {
                presenter.tryToLoadResponse();
            }
        });

        searchView.setOnQueryChangeListener((oldQuery, newQuery) -> presenter.setQueryString(newQuery));
    }

    @Override
    public void makeToast(String toastText) {
        Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void putListToAdapter(List<SearchItem> searchItems) {
//        itemAdapter = new ItemAdapter(searchItems);
        itemAdapter.setDataAdapter(searchItems);
        recyclerView.setAdapter(itemAdapter);
    }
}
