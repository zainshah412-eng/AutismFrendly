package com.AutismFriendlyWorld.tcc.Model.Places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Places_Translation_Outer
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("place_id")
    @Expose
    int place_id;
    @SerializedName("locale")
    @Expose
    String locale;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("description")
    @Expose
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
