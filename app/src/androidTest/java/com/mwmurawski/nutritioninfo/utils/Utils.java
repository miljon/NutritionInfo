package com.mwmurawski.nutritioninfo.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

public abstract class Utils {


    public static ViewAction setText(final String text) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(FloatingSearchView.class));
            }

            @Override
            public String getDescription() {
                return "Change search text";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((FloatingSearchView) view).setSearchText(text);
            }
        };
    }
}
