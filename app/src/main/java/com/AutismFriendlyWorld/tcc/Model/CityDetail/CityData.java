package com.AutismFriendlyWorld.tcc.Model.CityDetail;

import com.AutismFriendlyWorld.tcc.Model.CityDetail.Amenities.Amenity;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.Category.Category_CityDetailModel;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.City.City;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.Features.Feature;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.OtherCities.OtherCity;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.PlacesByCategory.PlacesByCategory;
import com.AutismFriendlyWorld.tcc.Model.Inspiration.Category;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.place_Types.Place_type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityData
{

    @SerializedName("city")
    @Expose
    City cityList;
    @SerializedName("categories")
    @Expose
    List<Category_CityDetailModel> categoryList;
    @SerializedName("features")
    @Expose
    List<Feature> featureList;
    @SerializedName("places_by_category")
    @Expose
    List<PlacesByCategory> placesByCategoryList;
    @SerializedName("other_cities")
    @Expose
    List<OtherCity> otherCityList;
    @SerializedName("cat_slug")
    @Expose
    String cat_slug;
    @SerializedName("place_types")
    @Expose
    List<Place_type> place_typeList;
    @SerializedName("amenities")
    @Expose
    List<Amenity> amenityList;


    public City getCityList() {
        return cityList;
    }

    public void setCityList(City cityList) {
        this.cityList = cityList;
    }

    public List<Category_CityDetailModel> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category_CityDetailModel> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public List<PlacesByCategory> getPlacesByCategoryList() {
        return placesByCategoryList;
    }

    public void setPlacesByCategoryList(List<PlacesByCategory> placesByCategoryList) {
        this.placesByCategoryList = placesByCategoryList;
    }

    public List<OtherCity> getOtherCityList() {
        return otherCityList;
    }

    public void setOtherCityList(List<OtherCity> otherCityList) {
        this.otherCityList = otherCityList;
    }

    public String getCat_slug() {
        return cat_slug;
    }

    public void setCat_slug(String cat_slug) {
        this.cat_slug = cat_slug;
    }

    public List<Place_type> getPlace_typeList() {
        return place_typeList;
    }

    public void setPlace_typeList(List<Place_type> place_typeList) {
        this.place_typeList = place_typeList;
    }

    public List<Amenity> getAmenityList() {
        return amenityList;
    }

    public void setAmenityList(List<Amenity> amenityList) {
        this.amenityList = amenityList;
    }
}
