package com.AutismFriendlyWorld.tcc.Model.Cities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City_ServerData
{
    @SerializedName("code")
    @Expose
    int code;
    @SerializedName("success")
    @Expose
    String success;
    @SerializedName("data")
    @Expose
    List<My_Cities_Model> myCitiesModelList;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<My_Cities_Model> getMyCitiesModelList() {
        return myCitiesModelList;
    }

    public void setMyCitiesModelList(List<My_Cities_Model> myCitiesModelList) {
        this.myCitiesModelList = myCitiesModelList;
    }
}
