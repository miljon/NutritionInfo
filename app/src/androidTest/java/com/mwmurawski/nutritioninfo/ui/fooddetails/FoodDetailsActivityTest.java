package com.mwmurawski.nutritioninfo.ui.fooddetails;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mwmurawski.nutritioninfo.AppTest;
import com.mwmurawski.nutritioninfo.CustomTestRule;
import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.di.component.ApplicationComponentTest;
import com.mwmurawski.nutritioninfo.utils.AppConstants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import okio.Okio;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class FoodDetailsActivityTest {

    private final String RESPONSE_DETAILS_FILE = "raw/resp_details.json";

//    @Inject SchedulerProvider scheduler;

    private MockWebServer server;
    private Buffer buffer;

    @Rule
    public CustomTestRule<FoodDetailsActivity> activity = new CustomTestRule<>(FoodDetailsActivity.class, true, false, new CustomTestRule.OnInitListener() {
        @Override
        public void onInit(String mockServerUrl) {
            AppConstants.BASE_URL = mockServerUrl;
        }
    });

    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest appTest = (AppTest) instrumentation.getTargetContext().getApplicationContext();
        ApplicationComponentTest activityComponentTest = (ApplicationComponentTest) appTest.getApplicationComponent();
        activityComponentTest.inject(this);

        server = activity.getMockWebServer();
        buffer = new Buffer();

        buffer.writeAll(Okio.source(getClass().getClassLoader().getResourceAsStream(RESPONSE_DETAILS_FILE)));
        server.enqueue(new MockResponse().setBody(buffer));

    }


    @Test
    public void checkIfDetailsLoadsCorrectly() throws Exception{

        // Launch the activity
        activity.launchActivity(new Intent());

//        ((TestScheduler)scheduler.ui()).triggerActions(); //triggers rxJava action

        //comparator doesn't like new line character '/n'.. i guess, couse its not working with one line compare
        onView(withId(R.id.details_info)).check(matches(withText(containsString("Margarine-like"))));
        onView(withId(R.id.details_info)).check(matches(withText(containsString("margarine-butter blend"))));
        onView(withId(R.id.details_info)).check(matches(withText(containsString("soybean oil and butter"))));
    }

}