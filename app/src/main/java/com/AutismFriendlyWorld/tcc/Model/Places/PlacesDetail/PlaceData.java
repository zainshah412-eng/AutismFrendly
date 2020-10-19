package com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceData
{

    @SerializedName("place")
    @Expose
    Place_PlaceDetail place_placeDetail;

    @SerializedName("city")
    @Expose
    City_PlaceDetail city_placeDetail;

    @SerializedName("amenities")
    @Expose
    List<AmenitiesPlaceDetail> amenitiesPlaceDetail;

    @SerializedName("categories")
    @Expose
    List<Categories_PlaceDetail> categories_placeDetail;

    @SerializedName("place_types")
    @Expose
    List<Place_types_PlaceDetail> place_types_placeDetail;


    @SerializedName("reviews")
    @Expose
    List<Reviews_PlaceDetail> reviews_placeDetail;

    @SerializedName("review_score_avg")
    @Expose
    float review_score_avg;

    @SerializedName("similar_places")
    @Expose
    List<Similar_places_PlaceDetail> similar_places_placeDetail;

    public Place_PlaceDetail getPlace_placeDetail() {
        return place_placeDetail;
    }

    public void setPlace_placeDetail(Place_PlaceDetail place_placeDetail) {
        this.place_placeDetail = place_placeDetail;
    }

    public City_PlaceDetail getCity_placeDetail() {
        return city_placeDetail;
    }

    public void setCity_placeDetail(City_PlaceDetail city_placeDetail) {
        this.city_placeDetail = city_placeDetail;
    }

    public List<AmenitiesPlaceDetail> getAmenitiesPlaceDetail() {
        return amenitiesPlaceDetail;
    }

    public void setAmenitiesPlaceDetail(List<AmenitiesPlaceDetail> amenitiesPlaceDetail) {
        this.amenitiesPlaceDetail = amenitiesPlaceDetail;
    }

    public List<Categories_PlaceDetail> getCategories_placeDetail() {
        return categories_placeDetail;
    }

    public void setCategories_placeDetail(List<Categories_PlaceDetail> categories_placeDetail) {
        this.categories_placeDetail = categories_placeDetail;
    }

    public List<Place_types_PlaceDetail> getPlace_types_placeDetail() {
        return place_types_placeDetail;
    }

    public void setPlace_types_placeDetail(List<Place_types_PlaceDetail> place_types_placeDetail) {
        this.place_types_placeDetail = place_types_placeDetail;
    }

    public List<Reviews_PlaceDetail> getReviews_placeDetail() {
        return reviews_placeDetail;
    }

    public void setReviews_placeDetail(List<Reviews_PlaceDetail> reviews_placeDetail) {
        this.reviews_placeDetail = reviews_placeDetail;
    }

    public float getReview_score_avg() {
        return review_score_avg;
    }

    public void setReview_score_avg(float review_score_avg) {
        this.review_score_avg = review_score_avg;
    }

    public List<Similar_places_PlaceDetail> getSimilar_places_placeDetail() {
        return similar_places_placeDetail;
    }

    public void setSimilar_places_placeDetail(List<Similar_places_PlaceDetail> similar_places_placeDetail) {
        this.similar_places_placeDetail = similar_places_placeDetail;
    }
}
