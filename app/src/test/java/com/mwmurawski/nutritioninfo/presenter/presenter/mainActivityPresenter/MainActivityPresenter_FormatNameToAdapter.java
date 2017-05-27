package com.mwmurawski.nutritioninfo.presenter.presenter.mainActivityPresenter;

import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class MainActivityPresenter_FormatNameToAdapter {

    @Parameterized.Parameter(0)
    public String inputString;
    @Parameterized.Parameter(1)
    public String outputString;



    private MainActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenter();
    }


    @Test
    public void formatNameToAdapter() throws Exception {
        SearchItem searchItem = mock(SearchItem.class);
        when(searchItem.getName()).thenReturn(inputString);

        String formattedString = presenter.formatNameToAdapter(searchItem);
        assertEquals(outputString, formattedString);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> c = new ArrayList<>();
        String in,ou;
        //1
        in = "PLUM, MASHUPS, ORGANIC APPLE SAUCE + STRAWBERRIES & BANANAS, STRAWBERRY BANANA!, UPC: 846675002198";
        ou = "Plum\nMashups\nOrganic apple sauce + strawberries & bananas\nStrawberry banana!";
        c.add(new Object[]{in,ou});
        //2
        in = "AHOLD, HARICOTS VERTS LIGHTLY SEASONED FRENCH GREEN BEANS WITH FINISHING BUTTER, UPC: 688267136344";
        ou = "Ahold\nHaricots verts lightly seasoned french green beans with finishing butter";
        c.add(new Object[]{in,ou});

        return c;
    }
}
