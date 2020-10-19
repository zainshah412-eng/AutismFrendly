package com.AutismFriendlyWorld.tcc.Model.Places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceModel
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("city_id")
    @Expose
    int city_id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("slug")
    @Expose
    String slug;
    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("city")
    @Expose
    Places_City_Data places_city_data;
    @SerializedName("translations")
    @Expose
    List<Places_Translation_Outer> places_translation_outerList;


    public List<Places_Translation_Outer> getPlaces_translation_outerList() {
        return places_translation_outerList;
    }

    public void setPlaces_translation_outerList(List<Places_Translation_Outer> places_translation_outerList) {
        this.places_translation_outerList = places_translation_outerList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Places_City_Data getPlaces_city_data() {
        return places_city_data;
    }

    public void setPlaces_city_data(Places_City_Data places_city_data) {
        this.places_city_data = places_city_data;
    }
}
