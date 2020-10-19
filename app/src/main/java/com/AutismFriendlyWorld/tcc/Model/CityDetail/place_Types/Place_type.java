package com.AutismFriendlyWorld.tcc.Model.CityDetail.place_Types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Place_type
{

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("category_id")
    @Expose
    int category_id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("created_at")
    @Expose
    String created_at;

    @SerializedName("updated_at")
    @Expose
    String updated_at;

    @SerializedName("translations")
    @Expose
    List<Place_Type_translation> place_type_translationList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<Place_Type_translation> getPlace_type_translationList() {
        return place_type_translationList;
    }

    public void setPlace_type_translationList(List<Place_Type_translation> place_type_translationList) {
        this.place_type_translationList = place_type_translationList;
    }
}
