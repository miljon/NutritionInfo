package com.mwmurawski.nutritioninfo.ui.fooddetails.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.ui.fooddetails.FoodDetailsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.DetailsViewHolder> implements ItemDetailsAdapterView{

    private List<Nutrient> nutrientList;
    private FoodDetailsPresenter presenter;

    public ItemDetailsAdapter(List<Nutrient> nutrientList, FoodDetailsPresenter presenter) {
        this.nutrientList = nutrientList;
        this.presenter = presenter;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_view, parent, false);
        return new DetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        final Nutrient nutrient = nutrientList.get(position);
        holder.nutritionName.setText(presenter.getNutritionName(nutrient));
        holder.nutritionValue.setText(presenter.getNutritionValueAndUnit(nutrient));
    }

    @Override
    public int getItemCount() {
        return nutrientList.size();
    }

    @Override
    public void setData(List<Nutrient> listOfItems) {
        this.nutrientList = listOfItems;
    }

    @Override
    public void setPresenter(FoodDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Static View Holder
     */
    static class DetailsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nutrition_name) TextView nutritionName;
        @BindView(R.id.nutrition_value) TextView nutritionValue;

        DetailsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
