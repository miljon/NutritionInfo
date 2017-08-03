package com.mwmurawski.nutritioninfo;

import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenterTest;
import com.mwmurawski.nutritioninfo.ui.main.MainActivityPresenterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityPresenterTest.class,
        FoodDetailsPresenterTest.class
})
public class JunitTestSuite {
}
