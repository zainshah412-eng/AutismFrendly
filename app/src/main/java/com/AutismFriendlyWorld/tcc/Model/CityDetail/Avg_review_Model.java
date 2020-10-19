package com.AutismFriendlyWorld.tcc.Model.CityDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Avg_review_Model
{
    @SerializedName("aggregate")
    @Expose
    String aggregate;
    @SerializedName("place_id")
    @Expose
    String place_id;


    public String getAggregate() {
        return aggregate;
    }

    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }
}
