package com.mwmurawski.nutritioninfo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.cons.Cons;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.model.search.SearchResult;
import com.mwmurawski.nutritioninfo.model.service.SearchService;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerNetworkComponent;
import com.mwmurawski.nutritioninfo.view.recyclerview.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.floating_search_view) FloatingSearchView searchView;

    @BindView(R.id.main_recyclerview) RecyclerView recyclerView;

    @Inject Retrofit retrofit;
    SearchResult result;
    List<SearchItem> itemsList;
    ItemAdapter itemAdapter;
    private String queryString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerNetworkComponent.create().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(new ArrayList<SearchItem>());
        recyclerView.setAdapter(itemAdapter);

        search();
    }

    private void search() {
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                Log.d("MMurawski", "Focus!");
            }

            @Override
            public void onFocusCleared() {
                if (queryString != null && !queryString.isEmpty()) {
                    SearchService searchService = retrofit.create(SearchService.class);
                    Call<SearchResult> resultCall = searchService.search(Cons.API_KEY, "json", queryString);
                    resultCall.enqueue(new Callback<SearchResult>() {
                        @Override
                        public void onResponse(Call<SearchResult> call, final Response<SearchResult> response) {
                            if (response.code() == 200) {
                                result = response.body();
                                if (result != null) {
                                    itemsList = result.getSearchList().getSearchItems();

                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            itemAdapter.setDataAdapter(itemsList);
                                            itemAdapter.notifyDataSetChanged();
                                        }
                                    };
                                    if (itemsList != null && !itemsList.isEmpty()) {
                                        runOnUiThread(runnable);
                                    } else {
                                        toast("Empty list of items");
                                    }
                                } else {
                                    toast("Empty response body");
                                }
                            } else {
                                toast("Response error: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchResult> call, Throwable t) {
                            toast("Failure while getting response");
                        }
                    });


                } else {
                    toast("Empty search text");
                }
            }

        });

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                queryString = newQuery;
                Log.d("MMURAWSKI: ", "old: " + oldQuery + ", new: " + newQuery);
            }
        });
    }

    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
