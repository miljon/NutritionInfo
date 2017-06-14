package com.mwmurawski.nutritioninfo.presenter.presenter.mainActivityPresenter;

import com.mwmurawski.nutritioninfo.cons.Strings;
import com.mwmurawski.nutritioninfo.model.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.MainActivityView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Single;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({MainActivityPresenter.class})
public class MainActivityPresenter_QueryString {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock SearchRepository searchRepository;

    @Mock MainActivityView view;

    @Mock SearchResult searchResult;

    private MainActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenter(searchRepository);
        presenter.bindView(view);
    }

    @Test
    public void loadResponse() throws Exception {
        when(searchRepository.getSearchResult("Butter")).thenReturn(Single.just(searchResult));

        presenter.loadSearchResponse();
        verify(view, times(1)).makeToast(Strings.EMPTY_STRING);

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();
        verify(view, times(1)).showProgressBar();
    }

    @Test
    public void makeToast() throws Exception {

    }

    @Test
    public void setQueryString() throws Exception {
        //I know it is pointless...
        String queryString = "butter";
        presenter.setQueryString(queryString);
        assertEquals("String should be equal", queryString,presenter.getQueryString());
    }




}