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

public class MainActivityPresenter extends BasePresenter<MainActivityView>{

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject Retrofit retrofit;
    @Inject SearchRepository searchRepository;
    private String queryString = null;


    private MainActivityView mainActivityView;

    private List<SearchItem> itemList;


    public MainActivityPresenter() {
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
                            handleError(e, "network problem");
                        }
                    }));
        } else {
            makeToast("Error: empty text");
        }
    }


    private void handleError(Throwable throwable, String additionalInfo) {
        makeToast("Error: "+additionalInfo);
        Log.e("MMU", "Error, e: " + throwable.getLocalizedMessage());
    }


    private void handleResponse(SearchResult searchResult) {
        if (searchResult != null
                && searchResult.getSearchList() != null
                && searchResult.getSearchList().getSearchItems() != null
                && !searchResult.getSearchList().getSearchItems().isEmpty()) {
            mainActivityView.putListToAdapter(searchResult.getSearchList().getSearchItems());
        } else {
            handleError(new Throwable("Empty response"), "empty response");
        }
        mainActivityView.hideProgressBar();
        mainActivityView.setSwipeRefreshingToFalse();
    }


    public void makeToast(String toastText) {
        mainActivityView.makeToast(toastText);
    }


    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void refreshList() {
        loadResponse();
    }

    /**
     * Random json output:
     * "name": "PLUM, MASHUPS, ORGANIC APPLE SAUCE + STRAWBERRIES & BANANAS, STRAWBERRY BANANA!, UPC: 846675002198"
     * @param name read from json
     * @return formatted string with new line for every coma, if line contains "UPC:" then it is
     */
    public String formatNameToAdapter(String name){
        StringBuilder sb = new StringBuilder();
        String[] nameLines = name.split(",");
        for (int i = 0; i < nameLines.length-1; i++){
            if(!nameLines[i].contains("UPC:")) sb.append(nameLines[i].trim()).append("\n");
        }
        return sb.toString();
    }
}
