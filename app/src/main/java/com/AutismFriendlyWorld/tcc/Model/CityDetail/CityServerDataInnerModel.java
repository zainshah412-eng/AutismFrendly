package com.AutismFriendlyWorld.tcc.Model.CityDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityServerDataInnerModel
{
    @SerializedName("code")
    @Expose
    String code;
    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    CityData cityData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
    }
}
