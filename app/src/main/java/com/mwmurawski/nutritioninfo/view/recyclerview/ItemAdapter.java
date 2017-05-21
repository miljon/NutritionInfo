package com.mwmurawski.nutritioninfo.view.recyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.presenter.presenter.MainActivityPresenter;
import com.mwmurawski.nutritioninfo.view.interfaces.ItemAdapterInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements ItemAdapterInterface{

    /**
     * ViewHolder for Item
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.maintext_line1) TextView mainText1;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //ITEM ADAPTER CLASS
    private List<SearchItem> listOfItems;
    private MainActivityPresenter presenter;

    private final PublishSubject<String> publishSubject;

    public ItemAdapter(List<SearchItem> listOfItems, MainActivityPresenter presenter) {
        this.listOfItems = listOfItems;
        this.presenter = presenter;
        publishSubject = PublishSubject.create();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.makeToast("Item was clicked. ID: "+ itemView.getId()+", "+parent.getId());
            }
        });
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (holder.mainText1 != null) {
            SearchItem item = listOfItems.get(position);
            holder.mainText1.setText(presenter.formatNameToAdapter(item.getName()));
        }
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    public void setData(List<SearchItem> listOfItems){
        this.listOfItems = listOfItems;
    }

    @Override
    public void setPresenter(MainActivityPresenter presenter) {
        this.presenter = presenter;
    }
}
