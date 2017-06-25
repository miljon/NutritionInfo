package com.mwmurawski.nutritioninfo.ui.base;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Class is needed to inject presenter. Base activity has a generic and dagger don't like it.
 */
public class CoreActivity extends AppCompatActivity {

    @Inject protected PresenterCache presenterCache;

}
