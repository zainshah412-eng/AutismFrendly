package com.AutismFriendlyWorld.tcc.Model.Places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Places_City_Data
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("slug")
    @Expose
    String slug;
    @SerializedName("intro")
    @Expose
    String intro;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("translations")
    @Expose
    List<Places_Translation> places_translationList;


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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public List<Places_Translation> getPlaces_translationList() {
        return places_translationList;
    }

    public void setPlaces_translationList(List<Places_Translation> places_translationList) {
        this.places_translationList = places_translationList;
    }
}
