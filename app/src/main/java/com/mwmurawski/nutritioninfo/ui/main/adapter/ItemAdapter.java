package com.mwmurawski.nutritioninfo.ui.main.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mwmurawski.nutritioninfo.R;
import com.mwmurawski.nutritioninfo.data.db.model.search.SearchItem;
import com.mwmurawski.nutritioninfo.di.component.DaggerAdapterComponent;
import com.mwmurawski.nutritioninfo.ui.main.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements ItemAdapterView {

    /**
     * ViewHolder for Item
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.maintext_line1) TextView mainText1;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //ITEM ADAPTER CLASS
    private List<SearchItem> listOfItems;
    private MainPresenter presenter;

    @Inject CompositeDisposable compositeDisposable;

    private final PublishSubject<String> publishSubject;

    public ItemAdapter(List<SearchItem> listOfItems, MainPresenter presenter) {
        this.listOfItems = listOfItems;
        this.presenter = presenter;

        DaggerAdapterComponent.builder().build().inject(this);

        publishSubject = PublishSubject.create();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        if (holder.mainText1 != null) {
            final SearchItem item = listOfItems.get(position);
            holder.mainText1.setText(presenter.formatNameToAdapter(item));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    publishSubject.onNext(item.getNdbno());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    @Override
    public Observable<String> getNdbnoClickObservable(){
        return publishSubject;
    }


    public void setData(List<SearchItem> listOfItems){
        this.listOfItems = listOfItems;
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }
}
