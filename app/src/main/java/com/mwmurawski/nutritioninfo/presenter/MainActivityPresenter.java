package com.mwmurawski.nutritioninfo.presenter;

import com.mwmurawski.nutritioninfo.cons.Cons;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.model.service.SearchService;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerNetworkComponent;
import com.mwmurawski.nutritioninfo.view.MainActivityView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Mij on 2017-04-20.
 */

public class MainActivityPresenter {

    @Inject Retrofit retrofit;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private String queryString = null;

    private MainActivityView mainActivityView;

    private List<SearchItem> itemList;

    public MainActivityPresenter(MainActivityView mainActivityView) {
        attachView(mainActivityView);
        DaggerNetworkComponent.create().inject(this);
    }

    public void attachView(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    public boolean isAttachedToView(){
        return mainActivityView != null;
    }

    public void detachView() {
        mainActivityView = null;
    }

    private void loadResponse(final String queryString) {
        compositeDisposable.add(retrofit.create(SearchService.class)
                .search(Cons.API_KEY, Cons.JSON, queryString)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleError(Throwable throwable) {
    }

    private void handleResponse(SearchResult searchResult) {
        if (searchResult != null
                && searchResult.getSearchList() != null
                && searchResult.getSearchList().getSearchItems() != null
                && !searchResult.getSearchList().getSearchItems().isEmpty()) {
            mainActivityView.putListToAdapter(searchResult.getSearchList().getSearchItems());
        } else {
            makeToast("Error: empty response");
        }
    }

    private void makeToast(String toastText) {
        mainActivityView.makeToast(toastText);
    }

    public void tryToLoadResponse() {
        if (queryString != null && !queryString.isEmpty()){
            loadResponse(queryString);
        }else {
            makeToast("Error: empty text");
        }
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
