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
    @SerializedName("sources")
    @Expose
    private List<Source> sources = null;
    @SerializedName("footnotes")
    @Expose
    private List<Object> footnotes = null;
    @SerializedName("langual")
    @Expose
    private List<Object> langual = null;

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

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public List<Object> getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(List<Object> footnotes) {
        this.footnotes = footnotes;
    }

    public List<Object> getLangual() {
        return langual;
    }

    public void setLangual(List<Object> langual) {
        this.langual = langual;
    }

}
