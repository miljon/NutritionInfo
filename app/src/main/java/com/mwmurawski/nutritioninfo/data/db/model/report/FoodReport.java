package com.mwmurawski.nutritioninfo.data.db.model.report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodReport {

    @SerializedName("report")
    @Expose
    private Report report;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

}