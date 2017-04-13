package com.mwmurawski.nutritioninfo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.mwmurawski.nutritioninfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.floating_search_view) FloatingSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        searchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {
                Log.d("MMU", "opened");
            }

            @Override
            public void onMenuClosed() {
                Log.d("MMU", "closed");
            }
        });
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery

                //pass them on to the search view
//                searchView.swapSuggestions(newSuggestions);
            }
        });

    }
}
