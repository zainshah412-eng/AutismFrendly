package com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail;

import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.City.Country_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place.Translation_PlaceDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City_PlaceDetail
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("intro")
    @Expose
    private Object intro;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("best_time_to_visit")
    @Expose
    private String bestTimeToVisit;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("seo_title")
    @Expose
    private String seoTitle;
    @SerializedName("seo_description")
    @Expose
    private Object seoDescription;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("country")
    @Expose
    private Country_PlaceDetail country;
    @SerializedName("translations")
    @Expose
    private List<Translation_PlaceDetail> translations = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
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

    public Object getIntro() {
        return intro;
    }

    public void setIntro(Object intro) {
        this.intro = intro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public Object getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(Object seoDescription) {
        this.seoDescription = seoDescription;
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

    public Country_PlaceDetail getCountry() {
        return country;
    }

    public void setCountry(Country_PlaceDetail country) {
        this.country = country;
    }

    public List<Translation_PlaceDetail> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation_PlaceDetail> translations) {
        this.translations = translations;
    }
}
