package com.mwmurawski.nutritioninfo.ui.fooddetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.ui.base.BaseActivity;
import com.mwmurawski.nutritioninfo.ui.fooddetails.adapter.ItemDetailsAdapterView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class FoodDetailsActivity extends BaseActivity<FoodDetailsPresenter> implements FoodDetailsView {

    @BindView(R.id.details_info) TextView detailsInfo;
    @BindView(R.id.details_recyclerview) RecyclerView detailsRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Inject ItemDetailsAdapterView itemAdapter;

    private String ndbno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        ndbno = intent.getStringExtra("ndbno");

        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailsRecyclerView.hasFixedSize();
        detailsRecyclerView.setAdapter((RecyclerView.Adapter) itemAdapter);

        presenter.loadNutritionDetails(ndbno);
    }

    @Override
    protected int getLayoutFile() {
        return R.layout.activity_food_details;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void injectPresenter() {
        setPresenter(getActivityComponent().getFoodDetailsPresenter());
    }

    @Override
    public void assignPresenterValuesToViewAfterRestore() {
        itemAdapter.setPresenter(presenter);
        itemAdapter.setData(presenter.getItemList());
    }

    @Override
    public void comebackToMainActivity() {
        onBackPressed();
    }

    @Override
    public void showNutritionDetails(String name, List<Nutrient> nutrients) {
        //set name
        detailsInfo.setText(name);

        //set list of nutritions
        itemAdapter.setData(nutrients);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
