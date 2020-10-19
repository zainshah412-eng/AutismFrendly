package com.AutismFriendlyWorld.tcc.Model.CityDetail.Features;

import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place_PlaceDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feature
{
    @SerializedName("category_id")
    @Expose
    int category_id;
    @SerializedName("category_name")
    @Expose
    String category_name;
    @SerializedName("category_slug")
    @Expose
    String category_slug;
    @SerializedName("feature_title")
    @Expose
    String feature_title;
    @SerializedName("places")
    @Expose
    List<Place_PlaceDetail> placesList;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_slug() {
        return category_slug;
    }

    public void setCategory_slug(String category_slug) {
        this.category_slug = category_slug;
    }

    public String getFeature_title() {
        return feature_title;
    }

    public void setFeature_title(String feature_title) {
        this.feature_title = feature_title;
    }

    public List<Place_PlaceDetail> getPlacesList() {
        return placesList;
    }

    public void setPlacesList(List<Place_PlaceDetail> placesList) {
        this.placesList = placesList;
    }
}
