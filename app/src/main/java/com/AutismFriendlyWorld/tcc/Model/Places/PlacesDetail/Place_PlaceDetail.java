package com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail;

import com.AutismFriendlyWorld.tcc.Model.CityDetail.Avg_review_Model;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.place_Types.Place_type;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place.OpeningHour_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place.Socail_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place.Translation_PlaceDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Place_PlaceDetail
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("category")
    @Expose
    private List<String> category = null;
    @SerializedName("place_type")
    @Expose
    private List<String> placeType = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price_range")
    @Expose
    private Integer priceRange;
    @SerializedName("amenities")
    @Expose
    private List<String> amenities = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("social")
    @Expose
    private List<Socail_PlaceDetail> social = null;
    @SerializedName("opening_hour")
    @Expose
    private List<OpeningHour_PlaceDetail> openingHour = null;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("gallery")
    @Expose
    private List<String> gallery = null;
    @SerializedName("video")
    @Expose
    private Object video;
    @SerializedName("booking_type")
    @Expose
    private Integer bookingType;
    @SerializedName("link_bookingcom")
    @Expose
    private Object linkBookingcom;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("seo_title")
    @Expose
    private String seoTitle;
    @SerializedName("seo_description")
    @Expose
    private String seoDescription;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("reviews_count")
    @Expose
    private int reviews_count;

    @SerializedName("wish_list_count")
    @Expose
    private int wish_list_count;
    @SerializedName("place_types")
    @Expose
    private List<Place_type> place_types;
    @SerializedName("avg_review")
    @Expose
    private List<Avg_review_Model> avg_review;

    @SerializedName("translations")
    @Expose
    private List<Translation_PlaceDetail> translations = null;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getPlaceType() {
        return placeType;
    }

    public void setPlaceType(List<String> placeType) {
        this.placeType = placeType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Integer priceRange) {
        this.priceRange = priceRange;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Socail_PlaceDetail> getSocial() {
        return social;
    }

    public void setSocial(List<Socail_PlaceDetail> social) {
        this.social = social;
    }

    public List<OpeningHour_PlaceDetail> getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(List<OpeningHour_PlaceDetail> openingHour) {
        this.openingHour = openingHour;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public Integer getBookingType() {
        return bookingType;
    }

    public void setBookingType(Integer bookingType) {
        this.bookingType = bookingType;
    }

    public Object getLinkBookingcom() {
        return linkBookingcom;
    }

    public void setLinkBookingcom(Object linkBookingcom) {
        this.linkBookingcom = linkBookingcom;
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

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Translation_PlaceDetail> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation_PlaceDetail> translations) {
        this.translations = translations;
    }


    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_list_count() {
        return wish_list_count;
    }

    public void setWish_list_count(int wish_list_count) {
        this.wish_list_count = wish_list_count;
    }

    public List<Place_type> getPlace_types() {
        return place_types;
    }

    public void setPlace_types(List<Place_type> place_types) {
        this.place_types = place_types;
    }

    public List<Avg_review_Model> getAvg_review() {
        return avg_review;
    }

    public void setAvg_review(List<Avg_review_Model> avg_review) {
        this.avg_review = avg_review;
    }
}
