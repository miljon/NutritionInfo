package com.mwmurawski.nutritioninfo.presenter.presenter.mainActivityPresenter;

import com.mwmurawski.nutritioninfo.data.db.model.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchList;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;
import com.mwmurawski.nutritioninfo.test.TestSchedulerProvider;
import com.mwmurawski.nutritioninfo.ui.main.MainView;
import com.mwmurawski.nutritioninfo.utils.AppConstants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainActivityPresenter_QueryString {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock SearchRepository searchRepository;

    @Mock SearchItem searchItem;
    @Mock SearchList searchList;
    @Mock MainView view;

    @Mock SearchResult searchResult;

    private TestScheduler testScheduler;
    private MainPresenter presenter;
    private List<SearchItem> searchItems;

    @Before
    public void setUp() throws Exception {
        testScheduler = new TestScheduler();

        presenter = new MainPresenter(searchRepository, new TestSchedulerProvider(testScheduler));
        presenter.bindView(view);
        presenter.setCompositeDisposable(new CompositeDisposable());
    }

    @Test
    public void loadResponse_empty() throws Exception {
        presenter.loadSearchResponse();

        verify(view, times(1)).makeToast(AppConstants.EMPTY_STRING);
    }


    @Test
    public void loadResponse_success() throws Exception {
        searchItems = new ArrayList<>();
        searchItems.add(searchItem);
        when(searchRepository.getSearchResult("Butter")).thenReturn(Single.just(searchResult));
        when(searchResult.getSearchList()).thenReturn(searchList);
        when(searchResult.getSearchList().getSearchItems()).thenReturn(searchItems);

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();

        testScheduler.triggerActions(); //triggers rxJava action

        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).putListToAdapter((List<SearchItem>) any());
    }

    @Test
    public void loadResponse_success_empty() throws Exception {
        searchItems = new ArrayList<>();

        when(searchRepository.getSearchResult("Butter")).thenReturn(Single.just(searchResult));
        when(searchResult.getSearchList()).thenReturn(searchList);
        when(searchResult.getSearchList().getSearchItems()).thenReturn(searchItems);

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();

        testScheduler.triggerActions(); //triggers rxJava action

        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).makeToast(contains(AppConstants.EMPTY_RESPONSE));
    }

    @Test
    public void loadResponse_error() throws Exception {
        when(searchRepository.getSearchResult("Butter")).thenReturn(Single.<SearchResult>error(new Throwable("Error")));

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();

        testScheduler.triggerActions(); //triggers rxJava action

        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).makeToast(contains(AppConstants.NETWORK_PROBLEM));

    }

    @Test
    public void makeToast() throws Exception {
        presenter.makeToast("message");
        verify(view, times(1)).makeToast("message");
    }

    @Test
    public void setQueryString() throws Exception {
        //I know it is pointless... but still..
        String queryString = "butter";
        presenter.setQueryString(queryString);
        assertEquals("String should be equal", queryString,presenter.getQueryString());
    }




}