package com.mwmurawski.nutritioninfo.ui.main;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.mwmurawski.nutritioninfo.AppTest;
import com.mwmurawski.nutritioninfo.CustomTestRule;
import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.di.component.ApplicationComponentTest;
import com.mwmurawski.nutritioninfo.utils.AppConstants;
import com.mwmurawski.nutritioninfo.utils.rx.schedulers.SchedulerProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.schedulers.TestScheduler;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import okio.Okio;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mwmurawski.nutritioninfo.utils.Utils.setText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.not;

/**
 * Instrumentation test, which will execute on an Android device.
 * TODO: Tests passes individually, but fails when run together, possible framework bug !!!!!!!!!!!!!!!!
 * TODO: link: https://issuetracker.google.com/issues/37082857
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private final String RESPONSE_FILE = "raw/resp.json";
    private final String RESPONSE_DETAILS_FILE = "raw/resp_details.json";
    private final String RESPONSE_ERROR_FILE = "raw/resp_error.json";
    private final String BUTTER_QUERY_STRING = "Butter";

    @Rule
    public CustomTestRule<MainActivity> activity = new CustomTestRule<>(MainActivity.class, true, false, new CustomTestRule.OnInitListener() {
        @Override
        public void onInit(String mockServerUrl) {
            AppConstants.BASE_URL = mockServerUrl;
        }
    });

    @Inject SchedulerProvider scheduler;

    private MockWebServer server;
    private Buffer buffer;

    private void mockResponse() throws IOException {
        buffer.writeAll(Okio.source(getClass().getClassLoader().getResourceAsStream(RESPONSE_FILE)));
        server.enqueue(new MockResponse().setBody(buffer).setBodyDelay(500, TimeUnit.MILLISECONDS));
    }

    private void triggerRxJavaAction() {
        ((TestScheduler) scheduler.ui()).triggerActions(); //triggers rxJava action
    }

    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest appTest = (AppTest) instrumentation.getTargetContext().getApplicationContext();
        ApplicationComponentTest activityComponentTest = (ApplicationComponentTest) appTest.getApplicationComponent();
        activityComponentTest.inject(this);

        server = activity.getMockWebServer();
        buffer = new Buffer();
    }

    @Test
    public void check_text_imput() throws Exception {

        mockResponse();

        activity.launchActivity(new Intent()); // Launch the activity

        onView(ViewMatchers.withId(R.id.floating_search_view)).perform(click());
        onView(withId(R.id.floating_search_view)).perform(setText(BUTTER_QUERY_STRING));
        onView(withId(R.id.floating_search_view)).perform(pressBack());

        triggerRxJavaAction();

        assertEquals(BUTTER_QUERY_STRING, activity.getActivity().getPresenter().getQueryString());
        assertEquals(activity.getActivity().getPresenter().getItemList().size(), 100);

    }

    @Test
    public void check_serach_view_after_lost_focus() throws Exception {

        activity.launchActivity(new Intent()); // Launch the activity

        onView(ViewMatchers.withId(R.id.floating_search_view)).perform(click());
        onView(withId(R.id.floating_search_view)).perform(setText(BUTTER_QUERY_STRING));
        onView(withId(R.id.floating_search_view)).perform(pressBack());
        //checks toast!
        onView(withText(AppConstants.NETWORK_PROBLEM)).inRoot(withDecorView(not(activity.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

        assertEquals(BUTTER_QUERY_STRING, activity.getActivity().getPresenter().getQueryString());
    }

    @Test
    public void check_loaded_positions() throws IOException {

        mockResponse();

        activity.launchActivity(new Intent()); // Launch the activity

        onView(ViewMatchers.withId(R.id.floating_search_view)).perform(click());
        onView(withId(R.id.floating_search_view)).perform(setText(BUTTER_QUERY_STRING));
        onView(withId(R.id.floating_search_view)).perform(pressBack());

        triggerRxJavaAction();

        buffer = new Buffer();
        buffer.writeAll(Okio.source(getClass().getClassLoader().getResourceAsStream(RESPONSE_DETAILS_FILE)));
        server.enqueue(new MockResponse().setBody(buffer));

        onView(withId(R.id.main_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        triggerRxJavaAction();

        intended(hasExtra("ndbno", "04585"));
    }
}
