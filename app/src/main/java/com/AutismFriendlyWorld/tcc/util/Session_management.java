package com.AutismFriendlyWorld.tcc.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import com.AutismFriendlyWorld.tcc.Activity.LogInActivity;

import java.util.HashMap;

import static com.AutismFriendlyWorld.tcc.Config.BaseURL.IS_LOGIN;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_ID;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_LATITIDUE;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_LONGITUDE;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_NAME;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_EMAIL;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_PASSWORD;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_PHONE_NO;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_IMAGE;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_USER_GCM_CODE;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_USER_IOS_TOKEN;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_USER_STATUS;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_VEHICLE_COLOR;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_VEHICLE_MODEL;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_VEHICLE_NUMBER;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.KEY_VEHICLE_TYPE;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.PREFS_NAME;
import static com.AutismFriendlyWorld.tcc.Config.BaseURL.PREFS_NAME2;


public class Session_management {

    SharedPreferences prefs;
    SharedPreferences prefs2;

    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor2;
    Context context;
    int PRIVATE_MODE = 0;

    public Session_management(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
        editor = prefs.edit();
        prefs2 = context.getSharedPreferences(PREFS_NAME2, PRIVATE_MODE);
        editor2 = prefs2.edit();
    }




    //Store Data
    public void createLoginSession(String id, String name, String email,String password,String phone, String image,
                                   String latitide, String longitute, String vechicle_type,String vechicle_number,
                                   String vehicle_model,String vehicle_color,String user_gcm_code,String user_ios_token, String user_status)
    {
      //  editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_PHONE_NO, phone);
        editor.putString(KEY_IMAGE, image);
        editor.putString(KEY_LATITIDUE, latitide);
        editor.putString(KEY_LONGITUDE, longitute);
        editor.putString(KEY_VEHICLE_TYPE, vechicle_type);
        editor.putString(KEY_VEHICLE_NUMBER, vechicle_number);
        editor.putString(KEY_VEHICLE_MODEL, vehicle_model);
        editor.putString(KEY_VEHICLE_COLOR, vehicle_color);
        editor.putString(KEY_USER_GCM_CODE, user_gcm_code);
        editor.putString(KEY_USER_IOS_TOKEN, user_ios_token);
        editor.putString(KEY_USER_STATUS, user_status);

        editor.commit();
    }

    public void checkLogin() {

        if (!this.isLoggedIn())
        {
            Intent loginsucces = new Intent(context, LogInActivity.class);
            loginsucces.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            loginsucces.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(loginsucces);
        }
    }

    //Store And Use Data

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_ID, prefs.getString(KEY_ID, null));
        user.put(KEY_NAME, prefs.getString(KEY_NAME, null));
        user.put(KEY_PHONE_NO, prefs.getString(KEY_PHONE_NO, null));
        user.put(KEY_EMAIL, prefs.getString(KEY_EMAIL, null));
        user.put(KEY_PASSWORD, prefs.getString(KEY_PASSWORD, null));
        user.put(KEY_IMAGE, prefs.getString(KEY_IMAGE, null));
        return user;
    }



    public void logoutSession()
    {
        editor.clear();
        editor.commit();
        Intent logout = new Intent(context, LogInActivity.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(logout);
    }

    // Get Login State
    public boolean isLoggedIn()
    {
        return prefs.getBoolean(IS_LOGIN, false);
    }

    public void updateData( String image) {

        editor.putString(KEY_IMAGE, image);

        editor.apply();
    }
}
