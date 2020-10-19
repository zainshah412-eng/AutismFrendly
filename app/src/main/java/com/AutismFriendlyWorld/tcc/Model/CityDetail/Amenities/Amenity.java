package com.AutismFriendlyWorld.tcc.Model.CityDetail.Amenities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Amenity
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("icon")
    @Expose
    String icon;
    @SerializedName("created_at")
    @Expose
    String created_at;
    @SerializedName("updated_at")
    @Expose
    String updated_at;
    @SerializedName("translations")
    @Expose
    List<Amenity_Tanslation> amenity_tanslationList;


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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<Amenity_Tanslation> getAmenity_tanslationList() {
        return amenity_tanslationList;
    }

    public void setAmenity_tanslationList(List<Amenity_Tanslation> amenity_tanslationList) {
        this.amenity_tanslationList = amenity_tanslationList;
    }
}
