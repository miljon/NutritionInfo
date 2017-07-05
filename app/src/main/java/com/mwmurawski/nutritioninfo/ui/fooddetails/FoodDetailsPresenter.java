package com.mwmurawski.nutritioninfo.ui.fooddetails;

import com.mwmurawski.nutritioninfo.data.db.model.report.FoodReport;
import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.data.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;

public class FoodDetailsPresenter extends BasePresenter<FoodDetailsView> {

    private SearchRepository searchRepository;

    private List<Nutrient> nutrientList;

    @Inject
    public FoodDetailsPresenter(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
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
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableSingleObserver<FoodReport>() {
                    @Override
                    public void onSuccess(@NonNull FoodReport foodReport) {
                        getView().hideProgressBar();
                        String formattedName = formatFoodName(foodReport.getReport().getFood().getName());
                        nutrientList = foodReport.getReport().getFood().getNutrients();
                        getView().showNutritionDetails(formattedName, nutrientList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().hideProgressBar();
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
