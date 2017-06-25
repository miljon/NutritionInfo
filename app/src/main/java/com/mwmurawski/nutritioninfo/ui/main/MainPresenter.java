package com.mwmurawski.nutritioninfo.ui.main;

import com.mwmurawski.nutritioninfo.utils.AppConstants;
import com.mwmurawski.nutritioninfo.utils.rx.schedulers.SchedulerProvider;
import com.mwmurawski.nutritioninfo.ui.base.BasePresenter;
import com.mwmurawski.nutritioninfo.data.db.model.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.di.component.DaggerMainPresenterComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {

    private SearchRepository searchRepository;
    private SchedulerProvider schedulerProvider;

    //Activity values to operate and restore
    private String queryString = null;
    private List<SearchItem> itemList;

    public MainPresenter() {
        DaggerMainPresenterComponent.builder().build().inject(this);
    }

    //I used constructor injections mostly for unit test, in test there will be different implementation of injected fields
    @Inject
    public MainPresenter(SearchRepository searchRepository, SchedulerProvider schedulerProvider) {
        this();
        this.searchRepository = searchRepository;
        this.schedulerProvider = schedulerProvider;
    }

    public void loadSearchResponse() {
        if (queryString != null && !queryString.isEmpty()) {
            getView().showProgressBar();
            getCompositeDisposable().add(searchRepository.getSearchResult(queryString)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribeWith(new DisposableSingleObserver<SearchResult>() {
                        @Override
                        public void onSuccess(@NonNull SearchResult searchResult) {
                            handleSearchResponse(searchResult);
                            getView().hideProgressBar();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            handleError(e, AppConstants.NETWORK_PROBLEM);
                            getView().hideProgressBar();
                        }
                    }));
        } else {
            makeToast(AppConstants.EMPTY_STRING);
        }
    }

    public void startObserveFoodItemsClick(Observable<String> observable){

        compositeDisposable.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@NonNull String ndbno) {
                        getView().startDetailsActivity(ndbno);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        handleError(e, AppConstants.FOOD_DETAILS_PROBLEM);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void handleSearchResponse(SearchResult searchResult) {
        if (searchResult != null
                && searchResult.getSearchList() != null
                && searchResult.getSearchList().getSearchItems() != null
                && !searchResult.getSearchList().getSearchItems().isEmpty()
                ) {
            itemList = searchResult.getSearchList().getSearchItems();
            getView().putListToAdapter(itemList);
        } else {
            handleError(new Throwable(AppConstants.EMPTY_RESPONSE), AppConstants.EMPTY_RESPONSE);
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

    /**
     * Handles error by showing error message in toast.
     * @param throwable thrown by error
     * @param additionalInfo additional info to show to user
     */
    private void handleError(Throwable throwable, String additionalInfo) {
        makeToast("Error (" + additionalInfo + "): " + throwable.getLocalizedMessage());
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
}
