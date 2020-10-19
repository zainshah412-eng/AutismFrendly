package com.AutismFriendlyWorld.tcc.Model.CityDetail.Category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category_CityDetailModel
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("priority")
    @Expose
    int priority;
    @SerializedName("slug")
    @Expose
    String slug;
    @SerializedName("place_count")
    @Expose
    int place_count;
    @SerializedName("feature_title")
    @Expose
    String feature_title;
    @SerializedName("translations")
    @Expose
    List<Category_Translation> category_translationList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getPlace_count() {
        return place_count;
    }

    public void setPlace_count(int place_count) {
        this.place_count = place_count;
    }

    public String getFeature_title() {
        return feature_title;
    }

    public void setFeature_title(String feature_title) {
        this.feature_title = feature_title;
    }

    public List<Category_Translation> getCategory_translationList() {
        return category_translationList;
    }

    public void setCategory_translationList(List<Category_Translation> category_translationList) {
        this.category_translationList = category_translationList;
    }

    public Category_CityDetailModel(int id, String name, int priority, String slug, int place_count, String feature_title, List<Category_Translation> category_translationList) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.slug = slug;
        this.place_count = place_count;
        this.feature_title = feature_title;
        this.category_translationList = category_translationList;
    }
}
