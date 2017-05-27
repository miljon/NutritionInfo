package com.mwmurawski.nutritioninfo.presenter.presenter;

import com.mwmurawski.nutritioninfo.model.report.FoodReport;
import com.mwmurawski.nutritioninfo.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.model.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.presenter.component.DaggerFoodDetailsPresenterComponent;
import com.mwmurawski.nutritioninfo.view.interfaces.FoodDetailsView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FoodDetailsPresenter extends BasePresenter<FoodDetailsView> {

    @Inject SearchRepository searchRepository;

    private List<Nutrient> nutrientList;

    public FoodDetailsPresenter() {
        DaggerFoodDetailsPresenterComponent.builder().build().inject(this);
    }

    public String setNutritionNameText(Nutrient nutrient) {
        return nutrient.getName();
    }

    public String setNutritionValueText(Nutrient nutrient) {
        return nutrient.getValue() + " " + nutrient.getUnit();
    }

    public void loadNutritionDetails(final String ndbno){
        getView().showProgressBar();
        getCompositeDisposable().add(searchRepository.getFoodReport(ndbno)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        getView().hideProgressBar();
                    }
                })
                .subscribeWith(new DisposableSingleObserver<FoodReport>() {
                    @Override
                    public void onSuccess(@NonNull FoodReport foodReport) {
                        String formattedName = formatFoodName(foodReport.getReport().getFood().getName());
                        nutrientList = foodReport.getReport().getFood().getNutrients();
                        getView().showNutritionDetails(formattedName, nutrientList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().makeToast("Error while loading details, try again");
                        getView().comebackToMainActivity();
                    }
                }));
    }

    private String formatFoodName(String name) {
        final String[] nameArray = name.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nameArray.length - 1; i++) {
            if (!(sb.length() == 0)) sb.append("\n");

            sb.append(nameArray[i]);
        }
        return sb.toString();
    }


    public List<Nutrient> getItemList() {
        return nutrientList;
    }
}
