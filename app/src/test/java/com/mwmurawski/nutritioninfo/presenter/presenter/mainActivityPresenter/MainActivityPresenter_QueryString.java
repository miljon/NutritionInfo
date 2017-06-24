package com.mwmurawski.nutritioninfo.presenter.presenter.mainActivityPresenter;

import com.mwmurawski.nutritioninfo.cons.Strings;
import com.mwmurawski.nutritioninfo.model.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.model.search.SearchList;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.test.TestSchedulerProvider;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;

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
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({MainActivityPresenter.class})
public class MainActivityPresenter_QueryString {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock SearchRepository searchRepository;

    @Mock SearchItem searchItem;
    @Mock SearchList searchList;
    @Mock MainActivityView view;

    @Mock SearchResult searchResult;

    private TestScheduler testScheduler;
    private MainActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        testScheduler = new TestScheduler();
        presenter = new MainActivityPresenter(searchRepository, new TestSchedulerProvider(testScheduler));
        presenter.bindView(view);
        presenter.setCompositeDisposable(new CompositeDisposable());
    }

    @Test
    public void loadResponse_empty() throws Exception {
        presenter.loadSearchResponse();
        verify(view, times(1)).makeToast(Strings.EMPTY_STRING);
    }


    @Test
    public void loadResponse_success() throws Exception {
        when(searchRepository.getSearchResult("Butter")).thenReturn(Single.just(searchResult));
        when(searchResult.getSearchList()).thenReturn(searchList);

        List<SearchItem> searchItems = new ArrayList<>();
        searchItems.add(searchItem);
        when(searchResult.getSearchList().getSearchItems()).thenReturn(searchItems);

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();
        verify(view, times(1)).showProgressBar();

        testScheduler.triggerActions(); //triggers rxJava action

        verify(view, times(1)).hideProgressBar();
        verify(view, times(1)).putListToAdapter((List<SearchItem>) any());
    }

    @Test
    public void loadResponse_success_empty() throws Exception {
        when(searchRepository.getSearchResult("Butter")).thenReturn(Single.just(searchResult));
        when(searchResult.getSearchList()).thenReturn(searchList);

        List<SearchItem> searchItems = new ArrayList<>();
        when(searchResult.getSearchList().getSearchItems()).thenReturn(searchItems);

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();
        verify(view, times(1)).showProgressBar();

        testScheduler.triggerActions(); //triggers rxJava action

        verify(view, times(1)).hideProgressBar();
        verify(view, atLeastOnce()).makeToast(contains(Strings.EMPTY_RESPONSE));
    }

    @Test
    public void loadResponse_error() throws Exception {
        when(searchRepository.getSearchResult("Butter")).thenReturn(Single.<SearchResult>error(new Throwable("Error")));

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();
        verify(view, times(1)).showProgressBar();

        testScheduler.triggerActions(); //triggers rxJava action

        verify(view, times(1)).hideProgressBar();
        verify(view, atLeastOnce()).makeToast(contains(Strings.NETWORK_PROBLEM));

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