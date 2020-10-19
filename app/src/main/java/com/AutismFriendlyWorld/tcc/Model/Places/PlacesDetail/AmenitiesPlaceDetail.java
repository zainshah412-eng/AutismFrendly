package com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail;

import com.AutismFriendlyWorld.tcc.Model.CityDetail.Amenities.Amenity_Tanslation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AmenitiesPlaceDetail
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("translations")
    @Expose
    private List<Amenity_Tanslation> translations = null;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Amenity_Tanslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Amenity_Tanslation> translations) {
        this.translations = translations;
    }
}
