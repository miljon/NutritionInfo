package com.mwmurawski.nutritioninfo.ui.fooddetails;

import com.mwmurawski.nutritioninfo.data.db.model.report.FoodReport;
import com.mwmurawski.nutritioninfo.data.db.model.report.Nutrient;
import com.mwmurawski.nutritioninfo.data.repository.SearchRepository;
import com.mwmurawski.nutritioninfo.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Presenter for FoodDetailsActivity
 */
public class FoodDetailsPresenter extends BasePresenter<FoodDetailsView> {

    private final SearchRepository searchRepository;
    private List<Nutrient> nutrientList;

    @Inject
    public FoodDetailsPresenter(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    /**
     * Gets name of nutrient
     * @param nutrient object to fetch name
     * @return name of nutrient
     */
    public String getNutritionName(final Nutrient nutrient) {
        return nutrient.getName();
    }

    /**
     * Helps to get nutrient values with proper units.
     * @param nutrient object to fetch proper value and units
     * @return formatted value and units in format: "[VALUE] [UNIT]"
     */
    public String getNutritionValueAndUnit(final Nutrient nutrient) {
        return nutrient.getValue() + " " + nutrient.getUnit();
    }

    /**
     * Loads nutrient information,
     * if success opens FoodDetailsActivity,
     * if error comes back to MainActivity, and show error message.
     * @param ndbno nutrient database number
     */
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

    /**
     * Formats name of food,
     * after every coma makes new line,
     * @param name name of food
     * @return formatted name
     */
    private String formatFoodName(final String name) {
        final String[] nameArray = name.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nameArray.length - 1; i++) {
            if (!(sb.length() == 0)) sb.append("\n");

            sb.append(nameArray[i]);
        }
        return sb.toString();
    }

    /**
     * Gets list of nutritions
     * @return list of nutritions
     */
    List<Nutrient> getItemList() {
        return nutrientList;
    }
}
