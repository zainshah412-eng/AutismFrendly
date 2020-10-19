package com.AutismFriendlyWorld.tcc.Model.CityDetail.OtherCities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country_CityDetail
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
    @SerializedName("priority")
    @Expose
    String priority;
    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("seo_title")
    @Expose
    String seo_title;
    @SerializedName("seo_description")
    @Expose
    String seo_description;
    @SerializedName("seo_cover_image")
    @Expose
    String seo_cover_image;
    @SerializedName("created_at")
    @Expose
    String created_at;
    @SerializedName("updated_at")
    @Expose
    String updated_at;

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSeo_title() {
        return seo_title;
    }

    public void setSeo_title(String seo_title) {
        this.seo_title = seo_title;
    }

    public String getSeo_description() {
        return seo_description;
    }

    public void setSeo_description(String seo_description) {
        this.seo_description = seo_description;
    }

    public String getSeo_cover_image() {
        return seo_cover_image;
    }

    public void setSeo_cover_image(String seo_cover_image) {
        this.seo_cover_image = seo_cover_image;
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
}
