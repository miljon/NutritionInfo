package com.mwmurawski.nutritioninfo;


import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsActivityTest;
import com.mwmurawski.nutritioninfo.ui.main.MainActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTest.class,
        FoodDetailsActivityTest.class
})
public class AndroidTestSuite {
}
