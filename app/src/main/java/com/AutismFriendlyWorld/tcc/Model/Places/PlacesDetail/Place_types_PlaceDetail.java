package com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail;

import com.AutismFriendlyWorld.tcc.Model.CityDetail.place_Types.Place_Type_translation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Place_types_PlaceDetail
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("translations")
    @Expose
    private List<Place_Type_translation> translations = null;

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

    public List<Place_Type_translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Place_Type_translation> translations) {
        this.translations = translations;
    }
}
