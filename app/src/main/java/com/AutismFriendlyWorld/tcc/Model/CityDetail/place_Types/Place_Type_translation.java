package com.AutismFriendlyWorld.tcc.Model.CityDetail.place_Types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place_Type_translation
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("place_type_id")
    @Expose
    int place_type_id;
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

    public int getPlace_type_id() {
        return place_type_id;
    }

    public void setPlace_type_id(int place_type_id) {
        this.place_type_id = place_type_id;
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
