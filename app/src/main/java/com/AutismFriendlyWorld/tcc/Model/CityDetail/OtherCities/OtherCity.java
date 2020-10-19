package com.AutismFriendlyWorld.tcc.Model.CityDetail.OtherCities;

import com.AutismFriendlyWorld.tcc.Model.Translations.Translations_City;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OtherCity
{
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("country_id")
    @Expose
    int country_id;
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
    @SerializedName("thumb")
    @Expose
    String thumb;
    @SerializedName("banner")
    @Expose
    String banner;
    @SerializedName("best_time_to_visit")
    @Expose
    String best_time_to_visit;
    @SerializedName("currency")
    @Expose
    String currency;
    @SerializedName("language")
    @Expose
    String language;
    @SerializedName("lat")
    @Expose
    String lat;
    @SerializedName("lng")
    @Expose
    String lng;
    @SerializedName("priority")
    @Expose
    String priority;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("seo_title")
    @Expose
    String seo_title;
    @SerializedName("seo_description")
    @Expose
    String seo_description;
    @SerializedName("created_at")
    @Expose
    String created_at;
    @SerializedName("updated_at")
    @Expose
    String updated_at;
    @SerializedName("places_count")
    @Expose
    int places_count;
    @SerializedName("country")
    @Expose
    Country_CityDetail country;
    @SerializedName("translations")
    @Expose
    List<Translations_City> translationsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
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

    public String getBest_time_to_visit() {
        return best_time_to_visit;
    }

    public void setBest_time_to_visit(String best_time_to_visit) {
        this.best_time_to_visit = best_time_to_visit;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public int getPlaces_count() {
        return places_count;
    }

    public void setPlaces_count(int places_count) {
        this.places_count = places_count;
    }

    public Country_CityDetail getCountry() {
        return country;
    }

    public void setCountry(Country_CityDetail country) {
        this.country = country;
    }

    public List<Translations_City> getTranslationsList() {
        return translationsList;
    }

    public void setTranslationsList(List<Translations_City> translationsList) {
        this.translationsList = translationsList;
    }
}
