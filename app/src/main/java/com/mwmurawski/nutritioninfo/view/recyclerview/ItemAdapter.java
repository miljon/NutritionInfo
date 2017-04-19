package com.mwmurawski.nutritioninfo.view.recyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    List<SearchItem> listOfItems;
    private final String COMA_SEPARATOR = ",";

    public ItemAdapter(List<SearchItem> listOfItems) {
        this.listOfItems = listOfItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        SearchItem item = listOfItems.get(position);
        String[] names = getNamesArray(item.getName());

        if (holder.mainText1 != null && holder.mainText2 != null) {
            holder.mainText1.setText(names[0]);
            holder.mainText2.setText(names[1]);
        }
    }

    public void setDataAdapter(List<SearchItem> listOfItems){
        this.listOfItems = listOfItems;
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    private String[] getNamesArray(String fullName) {
        return fullName.split(COMA_SEPARATOR);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.maintext_line1) TextView mainText1;
        @Nullable @BindView(R.id.maintext_line2) TextView mainText2;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
