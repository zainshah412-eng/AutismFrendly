package com.AutismFriendlyWorld.tcc.Model.CityDetail.Amenities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Amenity_Tanslation
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("amenities_id")
    @Expose
    String amenities_id;
    @SerializedName("locale")
    @Expose
    String locale;
    @SerializedName("name")
    @Expose
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmenities_id() {
        return amenities_id;
    }

    public void setAmenities_id(String amenities_id) {
        this.amenities_id = amenities_id;
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
}
