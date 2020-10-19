package com.AutismFriendlyWorld.tcc.Model.CityDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerData
{
    @SerializedName("")
    @Expose
    CityServerDataInnerModel cityServerDataInnerModel;

    public CityServerDataInnerModel getCityServerDataInnerModel() {
        return cityServerDataInnerModel;
    }

    public void setCityServerDataInnerModel(CityServerDataInnerModel cityServerDataInnerModel) {
        this.cityServerDataInnerModel = cityServerDataInnerModel;
    }
}
