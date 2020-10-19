package com.AutismFriendlyWorld.tcc.Model.CityDetail.Category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category_Translation
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("category_id")
    @Expose
    int category_id;
    @SerializedName("locale")
    @Expose
    String locale;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("feature_title")
    @Expose
    String feature_title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeature_title() {
        return feature_title;
    }

    public void setFeature_title(String feature_title) {
        this.feature_title = feature_title;
    }
}
