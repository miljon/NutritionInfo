package com.mwmurawski.nutritioninfo.ui.main;

import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchList;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.data.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.test.TestNetworkProviders;
import com.mwmurawski.nutritioninfo.test.TestSchedulerProvider;
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
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import okio.Okio;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainActivityPresenterTest {

    private final String RESPONSE_FILE = "raw/resp.json";
    private final String RESPONSE_ERROR_FILE = "raw/resp_error.json";

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock SearchRepository searchRepository;

    @Mock SearchItem searchItem;
    @Mock SearchList searchList;
    @Mock MainView view;

    @Mock SearchResult searchResult;

    private TestScheduler testScheduler;
    private MainPresenter presenter;

    private SearchRepository searchRepositoryMockServer;

    private MockWebServer server;
    private Buffer buffer;

    @Before
    public void setUp() throws Exception {
        testScheduler = new TestScheduler();

        presenter = new MainPresenter(searchRepository);
        presenter.setSchedulerProvider(new TestSchedulerProvider(testScheduler));
        presenter.setCompositeDisposable(new CompositeDisposable());
        presenter.bindView(view);

        server = new MockWebServer();
        searchRepositoryMockServer = new SearchRepository(TestNetworkProviders.getRetrofit(server));

        buffer = new Buffer();
    }

    @Test
    public void loadResponse_empty() throws Exception {
        presenter.loadSearchResponse();

        verify(view, times(1)).makeToast(AppConstants.EMPTY_STRING);
    }


    @Test
    public void loadResponse_success() throws Exception {

        buffer.writeAll(Okio.source(getClass().getClassLoader().getResourceAsStream(RESPONSE_FILE)));
        server.enqueue(new MockResponse().setBody(buffer));
        when(searchRepository.getSearchResult("Butter")).thenReturn(searchRepositoryMockServer.getSearchResult("Butter"));

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();

        testScheduler.triggerActions(); //triggers rxJava action

        assertEquals(presenter.getItemList().get(0).getNdbno(), "45093459");

        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).putListToAdapter((List<SearchItem>) any());
    }

    @Test
    public void loadResponse_success_empty() throws Exception {
        buffer.writeAll(Okio.source(getClass().getClassLoader().getResourceAsStream(RESPONSE_ERROR_FILE)));
        server.enqueue(new MockResponse().setBody(buffer));
        when(searchRepository.getSearchResult("Butter")).thenReturn(searchRepositoryMockServer.getSearchResult("Butter"));

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();

        testScheduler.triggerActions(); //triggers rxJava action

        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).makeToast(contains(AppConstants.EMPTY_RESPONSE));
    }

    @Test
    public void loadResponse_error() throws Exception {
        server.enqueue(new MockResponse().setBody(String.valueOf(Single.just(new Throwable("Error")))));
        when(searchRepository.getSearchResult("Butter")).thenReturn(searchRepositoryMockServer.getSearchResult("Butter"));

        presenter.setQueryString("Butter");
        presenter.loadSearchResponse();

        testScheduler.triggerActions(); //triggers rxJava action

        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).makeToast(contains(AppConstants.NETWORK_PROBLEM));
    }


    @Test
    public void startObserveFoodItemsClick_success() throws Exception {
        presenter.startObserveFoodItemsClick(Single.just("Butter"));

        testScheduler.triggerActions();

        verify(view).startDetailsActivity(anyString());
    }

    @Test
    public void startObserveFoodItemsClick_error() throws Exception {
        presenter.startObserveFoodItemsClick(Single.<String>error(new Throwable("Error")));

        testScheduler.triggerActions();

        verify(view).makeToast(contains("Error"));
    }

//    @Test
//    public void makeToast() throws Exception {
//        presenter.makeToast("message");
//        verify(view, times(1)).makeToast("message");
//    }

    @Test
    public void setQueryString() throws Exception {
        //I know it is pointless... but still..
        String queryString = "butter";
        presenter.setQueryString(queryString);
        assertEquals("String should be equal", queryString,presenter.getQueryString());
    }

    @Test
    public void getItemList() throws Exception {
        List<SearchItem> searchItems = new ArrayList<>();
        presenter.setItemList(new ArrayList<SearchItem>());
        assertEquals(presenter.getItemList(), searchItems);
    }
}