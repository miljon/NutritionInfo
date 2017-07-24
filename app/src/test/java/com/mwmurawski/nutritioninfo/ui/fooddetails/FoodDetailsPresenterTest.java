package com.mwmurawski.nutritioninfo.ui.fooddetails;

import com.mwmurawski.nutritioninfo.NetworkTestHelper;
import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.data.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.utils.rx.schedulers.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import okio.Okio;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class FoodDetailsPresenterTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final String RESPONSE_DETAILS_FILE = "raw/resp_details.json";

    @Mock FoodDetailsView view;

    private TestScheduler testScheduler;
    private MockWebServer server;
    private FoodDetailsPresenter presenter;
    private Buffer buffer;
    private SearchRepository searchRepository;

    private void mockResponse() throws Exception{
        buffer.writeAll(Okio.source(getClass().getClassLoader().getResourceAsStream(RESPONSE_DETAILS_FILE)));
        server.enqueue(new MockResponse().setBody(buffer));
        presenter.loadNutritionDetails("04585");
        testScheduler.triggerActions(); //triggers rxJava action
    }

    @Before
    public void setUp() throws Exception {
        testScheduler = new TestScheduler();

        NetworkTestHelper networkTestHelper = new NetworkTestHelper();
        server = networkTestHelper.provideMwMockWebServer();
        searchRepository = new SearchRepository(networkTestHelper.provideRetrofit());

        presenter = new FoodDetailsPresenter(searchRepository);
        presenter.setScheduler(new TestSchedulerProvider(testScheduler));
        presenter.setCompositeDisposable(new CompositeDisposable());
        presenter.bindView(view);

        buffer = new Buffer();

        mockResponse();
    }

    @Test
    public void getNutritionName() throws Exception {
        String expected = "Water";
        String actual = presenter.getNutritionName(presenter.getItemList().get(0));
        assertEquals(expected, actual);

        expected = "Energy";
        actual = presenter.getNutritionName(presenter.getItemList().get(1));
        assertEquals(expected, actual);

        expected = "Protein";
        actual = presenter.getNutritionName(presenter.getItemList().get(2));
        assertEquals(expected, actual);

        expected = "Total lipid (fat)";
        actual = presenter.getNutritionName(presenter.getItemList().get(3));
        assertEquals(expected, actual);
    }

    @Test
    public void getNutritionValueAndUnit() throws Exception {
        String expected = "17.07 g";
        String actual = presenter.getNutritionValueAndUnit(presenter.getItemList().get(0));
        assertEquals(expected, actual);

        expected = "727 kcal";
        actual = presenter.getNutritionValueAndUnit(presenter.getItemList().get(1));
        assertEquals(expected, actual);

        expected = "0.31 g";
        actual = presenter.getNutritionValueAndUnit(presenter.getItemList().get(2));
        assertEquals(expected, actual);

        expected = "80.32 g";
        actual = presenter.getNutritionValueAndUnit(presenter.getItemList().get(3));
        assertEquals(expected, actual);

    }

    @Test
    public void loadNutritionDetails() throws Exception {
        verify(view).showProgressBar();
        verify(view).hideProgressBar();
        verify(view).showNutritionDetails("Margarine-like\nmargarine-butter blend\nsoybean oil and butter", presenter.getItemList());
    }

    @Test
    public void getItemList() throws Exception {
        List<Nutrient> nutrients = presenter.getItemList();
        assertEquals(33, nutrients.size());

        Nutrient first = nutrients.get(0);
        assertEquals("Water", first.getName());
        assertEquals("g", first.getUnit());
        assertEquals("17.07", first.getValue());
    }
}