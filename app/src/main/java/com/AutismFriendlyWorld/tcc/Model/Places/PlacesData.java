package com.AutismFriendlyWorld.tcc.Model.Places;

import com.AutismFriendlyWorld.tcc.Model.CityDetail.Category.Category_CityDetailModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlacesData
{
    @SerializedName("places")
    @Expose
    List<PlaceModel> placeModelList;

    public List<PlaceModel> getPlaceModelList()
    {
        return placeModelList;
    }

    public void setPlaceModelList(List<PlaceModel> placeModelList)
    {
        this.placeModelList = placeModelList;
    }
}
