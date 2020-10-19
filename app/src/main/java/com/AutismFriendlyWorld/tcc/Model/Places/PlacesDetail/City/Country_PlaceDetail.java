package com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.City;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country_PlaceDetail
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("seo_title")
    @Expose
    private Object seoTitle;
    @SerializedName("seo_description")
    @Expose
    private Object seoDescription;
    @SerializedName("seo_cover_image")
    @Expose
    private Object seoCoverImage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(Object seoTitle) {
        this.seoTitle = seoTitle;
    }

    public Object getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(Object seoDescription) {
        this.seoDescription = seoDescription;
    }

    public Object getSeoCoverImage() {
        return seoCoverImage;
    }

    public void setSeoCoverImage(Object seoCoverImage) {
        this.seoCoverImage = seoCoverImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
