package com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail;

import com.AutismFriendlyWorld.tcc.Model.CityDetail.Category.Category_Translation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories_PlaceDetail
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
    @SerializedName("icon_map_marker")
    @Expose
    private String iconMapMarker;
    @SerializedName("feature_title")
    @Expose
    private Object featureTitle;
    @SerializedName("translations")
    @Expose
    private List<Category_Translation> translations = null;

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

    public String getIconMapMarker() {
        return iconMapMarker;
    }

    public void setIconMapMarker(String iconMapMarker) {
        this.iconMapMarker = iconMapMarker;
    }

    public Object getFeatureTitle() {
        return featureTitle;
    }

    public void setFeatureTitle(Object featureTitle) {
        this.featureTitle = featureTitle;
    }

    public List<Category_Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Category_Translation> translations) {
        this.translations = translations;
    }
}
