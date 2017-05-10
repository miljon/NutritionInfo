package com.mwmurawski.nutritioninfo.model.report;

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
    @SerializedName("sd")
    @Expose
    private String sd;
    @SerializedName("fg")
    @Expose
    private String fg;
    @SerializedName("sn")
    @Expose
    private String sn;
    @SerializedName("cn")
    @Expose
    private String cn;
    @SerializedName("manu")
    @Expose
    private String manu;
    @SerializedName("nf")
    @Expose
    private Double nf;
    @SerializedName("cf")
    @Expose
    private Double cf;
    @SerializedName("ff")
    @Expose
    private Double ff;
    @SerializedName("pf")
    @Expose
    private Double pf;
    @SerializedName("r")
    @Expose
    private String r;
    @SerializedName("rd")
    @Expose
    private String rd;
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

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getFg() {
        return fg;
    }

    public void setFg(String fg) {
        this.fg = fg;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public Double getNf() {
        return nf;
    }

    public void setNf(Double nf) {
        this.nf = nf;
    }

    public Double getCf() {
        return cf;
    }

    public void setCf(Double cf) {
        this.cf = cf;
    }

    public Double getFf() {
        return ff;
    }

    public void setFf(Double ff) {
        this.ff = ff;
    }

    public Double getPf() {
        return pf;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
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
