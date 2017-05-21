package com.mwmurawski.nutritioninfo.presenter.presenter;

import com.mwmurawski.nutritioninfo.model.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerMainActivityPresenterComponent;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    @Inject SearchRepository searchRepository;

    //Activity values to operate and restore
    private String queryString = null;
    private List<SearchItem> itemList;

    public MainActivityPresenter() {
        DaggerMainActivityPresenterComponent.builder().build().inject(this);

    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void loadResponse() {
        if (queryString != null && !queryString.isEmpty()) {
            getView().showProgressBar();
            getCompositeDisposable().add(searchRepository.getSearchResult(queryString)
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
        makeToast("Error (" + additionalInfo + "): " + throwable.getLocalizedMessage());
    }

    private void handleResponse(SearchResult searchResult) {
        if (searchResult != null
                && searchResult.getSearchList() != null
                && searchResult.getSearchList().getSearchItems() != null
                && !searchResult.getSearchList().getSearchItems().isEmpty()) {
            itemList = searchResult.getSearchList().getSearchItems();
            getView().putListToAdapter(itemList);
        } else {
            handleError(new Throwable("Empty response"), "empty response");
        }
        getView().hideProgressBar();
        getView().setSwipeRefreshingToFalse();
    }

    public void makeToast(String toastText) {
        getView().makeToast(toastText);
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
    public String formatNameToAdapter(String name) {
        StringBuilder sb = new StringBuilder();
        String[] nameLines = name.split(",");
        for (int i = 0; i < nameLines.length - 1; i++) {
            if (!(sb.length() == 0)) sb.append("\n");
            if (!nameLines[i].contains("UPC:")) {
                sb.append(nameLines[i].trim().substring(0, 1).toUpperCase())
                        .append(nameLines[i].trim().substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
}
