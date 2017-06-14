package com.mwmurawski.nutritioninfo.presenter.presenter;

import com.mwmurawski.nutritioninfo.cons.Strings;
import com.mwmurawski.nutritioninfo.model.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerMainActivityPresenterComponent;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
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

    //for unit test
    public MainActivityPresenter(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
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

    public void loadSearchResponse() {
        if (queryString != null && !queryString.isEmpty()) {
            getView().showProgressBar();
            getCompositeDisposable().add(searchRepository.getSearchResult(queryString)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(new Action() {
                        @Override
                        public void run() throws Exception {
                            getView().hideProgressBar();
                        }
                    })
                    .subscribeWith(new DisposableSingleObserver<SearchResult>() {
                        @Override
                        public void onSuccess(@NonNull SearchResult searchResult) {
                            handleSearchResponse(searchResult);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            handleError(e, Strings.NETWORK_PROBLEM);
                        }
                    }));
        } else {
            makeToast(Strings.EMPTY_STRING);
        }
    }

    public void startObserveFoodItemsClick(Observable<String> observable){

        compositeDisposable.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@NonNull String ndbno) {
                        startNutritionActivity(ndbno);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        handleError(e, Strings.FOOD_DETAILS_PROBLEM);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void startNutritionActivity(String ndbno){
        getView().startDetailsActivity(ndbno);
    }

    private void handleError(Throwable throwable, String additionalInfo) {
        makeToast("Error (" + additionalInfo + "): " + throwable.getLocalizedMessage());
    }

    private void handleSearchResponse(SearchResult searchResult) {
        if (searchResult != null
                && searchResult.getSearchList() != null
                && searchResult.getSearchList().getSearchItems() != null
                && !searchResult.getSearchList().getSearchItems().isEmpty()) {
            itemList = searchResult.getSearchList().getSearchItems();
            getView().putListToAdapter(itemList);
        } else {
            handleError(new Throwable(Strings.EMPTY_RESPONSE), Strings.EMPTY_RESPONSE);
        }
    }

    public void makeToast(String toastText) {
        getView().makeToast(toastText);
    }

    /**
     * Random json output:
     * "name": "PLUM, MASHUPS, ORGANIC APPLE SAUCE + STRAWBERRIES & BANANAS, STRAWBERRY BANANA!, UPC: 846675002198"
     * @param searchItem item from list of items provided by network request
     * @return formatted string with new line for every coma, if line contains "UPC:" then it is
     */
    public String formatNameToAdapter(SearchItem searchItem) {
        final String name = searchItem.getName();
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
