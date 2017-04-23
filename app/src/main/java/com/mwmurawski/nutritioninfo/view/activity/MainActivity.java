package com.mwmurawski.nutritioninfo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.cons.Cons;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.model.service.SearchService;
import com.mwmurawski.nutritioninfo.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerNetworkComponent;
import com.mwmurawski.nutritioninfo.view.recyclerview.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject Retrofit retrofit;

    @BindView(R.id.floating_search_view) FloatingSearchView searchView;
    @BindView(R.id.main_recyclerview) RecyclerView recyclerView;

    MainActivityPresenter mainActivityPresenter;

    SearchResult result;
    List<SearchItem> itemsList;

    CompositeDisposable compositeDisposable;

    ItemAdapter itemAdapter;
    private String queryString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerNetworkComponent.create().inject(this);

        mainActivityPresenter = new MainActivityPresenter();

        compositeDisposable = new CompositeDisposable();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(new ArrayList<>());
        recyclerView.setAdapter(itemAdapter);

        search();
    }

    private void search() {
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                Log.d("MMurawski", "Focus!");
            }

            @Override
            public void onFocusCleared() {
                if (queryString != null && !queryString.isEmpty()) {
                    loadResponse(queryString);
                } else {
                    toast("Empty search text");
                }
            }
        });

        searchView.setOnQueryChangeListener((oldQuery, newQuery) -> queryString = newQuery);
    }

    private void loadResponse(final String queryString) {
//        SearchService searchService = retrofit.create(SearchService.class);

//        Single<SearchResult> resultSingle = searchService.search(Cons.API_KEY, "json", queryString);
//        Observable<SearchResult> resultObservable = searchService.search(Cons.API_KEY, "json", queryString);

//        compositeDisposable.add(resultSingle
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::handleResponse, this::handleError));

        retrofit.create(SearchService.class)
                .search(Cons.API_KEY, "json", queryString)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError);

    }

    private void handleResponse(SearchResult searchResult) {
        if (searchResult != null && searchResult.getSearchList() != null && searchResult.getSearchList().getSearchItems() != null && !searchResult.getSearchList().getSearchItems().isEmpty()) {
            itemsList = searchResult.getSearchList().getSearchItems();
            itemAdapter.setDataAdapter(itemsList);
            itemAdapter.notifyDataSetChanged();
        } else {
            toast("Response error");
        }
    }

    private void handleError(Throwable throwable) {
        Log.e("Network error: ", throwable.getMessage());
    }

    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
