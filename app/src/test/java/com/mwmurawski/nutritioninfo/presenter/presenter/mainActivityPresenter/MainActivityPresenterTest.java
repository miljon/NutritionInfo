package com.mwmurawski.nutritioninfo.presenter.presenter.mainActivityPresenter;

import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MainActivityPresenterTest {



    private MainActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenter();
    }

    @Test
    public void loadResponse() throws Exception {

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