package com.mwmurawski.nutritioninfo.model.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;

import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    List<SearchItem> listOfItems;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        SearchItem item = listOfItems.get(position);
        String[] names = getNamesArray(item.getName());

        holder.mainText1.setText(names[0]);
        holder.mainText2.setText(names[1]);
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    private String[] getNamesArray(String fullName) {
        return fullName.split(",");
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.maintext_line1)
        TextView mainText1;
        @BindView(R.id.maintext_line2)
        TextView mainText2;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
