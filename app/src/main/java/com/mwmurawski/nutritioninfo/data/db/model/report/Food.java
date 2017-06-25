package com.mwmurawski.nutritioninfo.data.db.model.report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Food {

    @SerializedName("ndbno")
    @Expose
    private String ndbno;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ds")
    @Expose
    private String ds;
    @SerializedName("ru")
    @Expose
    private String ru;
    @SerializedName("nutrients")
    @Expose
    private List<Nutrient> nutrients = null;

    public String getNdbno() {
        return ndbno;
    }

    public void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

}