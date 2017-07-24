package com.mwmurawski.nutritioninfo;

import android.app.Activity;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import okhttp3.mockwebserver.MockWebServer;

public class CustomTestRule<E extends Activity> extends IntentsTestRule<E> {

    public interface OnInitListener{
        void onInit(String mockServerUrl);
    }

    private MockWebServer mockWebServer;
    private String mockWebServerHost;

    public CustomTestRule(Class<E> activityClass, OnInitListener listener) {
        super(activityClass);
        initMockWebServer(listener);
    }

    public CustomTestRule(Class<E> activityClass, boolean initialTouchMode, OnInitListener listener) {
        super(activityClass, initialTouchMode);
        initMockWebServer(listener);
    }

    public CustomTestRule(Class<E> activityClass, boolean initialTouchMode, boolean launchActivity, OnInitListener listener) {
        super(activityClass, initialTouchMode, launchActivity);
        initMockWebServer(listener);
    }

    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }

    public String getMockWebServerHost() {
        return mockWebServerHost;
    }

    private void initMockWebServer(OnInitListener listener){
        if (mockWebServer == null) {
            mockWebServer = new MockWebServer();
            mockWebServerHost = mockWebServer.url("/").toString();
            listener.onInit(mockWebServerHost);
        }
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
//        try {
//            mockWebServer.shutdown();
//        } catch (IOException e) {
//            throw new IllegalStateException(e.getMessage());
//        }
    }
}
