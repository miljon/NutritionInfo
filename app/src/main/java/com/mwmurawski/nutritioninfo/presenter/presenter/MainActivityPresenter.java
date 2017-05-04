package com.mwmurawski.nutritioninfo.presenter.presenter;

import android.util.Log;

import com.mwmurawski.nutritioninfo.model.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerNetworkComponent;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerRepositoryComponent;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivityPresenter {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject Retrofit retrofit;
    @Inject SearchRepository searchRepository;
    private String queryString = null;

    private MainActivityView mainActivityView;

    private List<SearchItem> itemList;


    public MainActivityPresenter(MainActivityView mainActivityView) {
        attachView(mainActivityView);
        DaggerNetworkComponent.create().inject(this);
        DaggerRepositoryComponent.create().inject(this);
    }


    public void attachView(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }


    public boolean isAttachedToView() {
        return mainActivityView != null;
    }


    public void detachView() {
        mainActivityView = null;
    }


    public void loadResponse() {
        if (queryString != null && !queryString.isEmpty()) {
            mainActivityView.showProgressBar();
            compositeDisposable.add(searchRepository.getSearchResult(queryString)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<SearchResult>() {
                        @Override
                        public void onSuccess(@NonNull SearchResult searchResult) {
                            handleResponse(searchResult);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            handleError(e);
                        }
                    }));
        } else {
            makeToast("Error: empty text");
        }
    }


    private void handleError(Throwable throwable) {
        makeToast("Error: network problem.");
        Log.e("MMU", "Network problem e: " + throwable.getLocalizedMessage());
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
        mainActivityView.hideProgressBar();
        mainActivityView.setSwipeRefreshingToFalse();
    }


    private void makeToast(String toastText) {
        mainActivityView.makeToast(toastText);
    }


    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void refreshList() {
        loadResponse();
    }
}
