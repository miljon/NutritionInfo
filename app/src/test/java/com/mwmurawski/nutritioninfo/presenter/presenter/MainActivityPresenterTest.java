package com.mwmurawski.nutritioninfo.presenter.presenter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MainActivityPresenterTest {

    private final String inputString;
    private final String outputString;

    private MainActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenter();
    }

    public MainActivityPresenterTest(String inputString, String outputString) {
        this.inputString = inputString;
        this.outputString = outputString;
    }

    @Test
    public void getQueryString() throws Exception {

    }

    @Test
    public void getItemList() throws Exception {

    }

    @Test
    public void loadResponse() throws Exception {

    }

    @Test
    public void makeToast() throws Exception {

    }

    @Test
    public void setQueryString() throws Exception {

    }

    @Test
    public void refreshList() throws Exception {

    }

    @Test
    public void formatNameToAdapter() throws Exception {
        String formattedString = presenter.formatNameToAdapter(inputString);
        Assert.assertEquals(outputString, formattedString);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> c = new ArrayList<>();
        c.add(new Object[]{"PLUM, MASHUPS, ORGANIC APPLE SAUCE + STRAWBERRIES & BANANAS, STRAWBERRY BANANA!, UPC: 846675002198",
                "Plum\nMashups\nOrganic apple sauce + strawberries & bananas\nStrawberry banana!"});
        c.add(new Object[]{"AHOLD, HARICOTS VERTS LIGHTLY SEASONED FRENCH GREEN BEANS WITH FINISHING BUTTER, UPC: 688267136344",
                "Ahold\nHaricots verts lightly seasoned french green beans with finishing butter"});
        return c;
    }
}