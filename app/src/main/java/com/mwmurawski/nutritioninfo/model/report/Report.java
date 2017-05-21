package com.mwmurawski.nutritioninfo.model.report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Report {

    @SerializedName("sr")
    @Expose
    private String sr;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("food")
    @Expose
    private Food food;
    @SerializedName("footnotes")
    @Expose
    private List<Object> footnotes = null;

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public List<Object> getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(List<Object> footnotes) {
        this.footnotes = footnotes;
    }

}