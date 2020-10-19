package com.AutismFriendlyWorld.tcc.Model.Translations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translations_City
{

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("city_id")
    @Expose
    int city_id;
    @SerializedName("locale")
    @Expose
    String locale;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("intro")
    @Expose
    String intro;
    @SerializedName("description")
    @Expose
    String description;

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
