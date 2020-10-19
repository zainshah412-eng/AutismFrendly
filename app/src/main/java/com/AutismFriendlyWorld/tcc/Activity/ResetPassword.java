package com.AutismFriendlyWorld.tcc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.ConnectivityReceiver;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPassword extends AppCompatActivity {

    private static String TAG = ResetPassword.class.getSimpleName();
    String lan;
    SharedPreferences preferences;


    RelativeLayout btn_Sign_in;
    EditText et_login_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        preferences = getSharedPreferences("lan", MODE_PRIVATE);
        lan=preferences.getString("language","");


        et_login_email = (EditText) findViewById(R.id.et_login_email);
        btn_Sign_in = (RelativeLayout) findViewById(R.id.btn_Sign_in);

        btn_Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptForgot();
            }
        });
    }


    private void attemptForgot() {

//        tv_email.setText(getResources().getString(R.string.tv_login_email));
//
//        tv_email.setTextColor(getResources().getColor(R.color.dark_gray));

        String getemail = et_login_email.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(getemail)) {
      //      tv_email.setTextColor(getResources().getColor(R.color.black));
            focusView = et_login_email;
            et_login_email.setError("Invaild Email");
            cancel = true;
        } else if (!isEmailValid(getemail)) {
         //   tv_email.setText(getResources().getString(R.string.invalide_email_address));
         //   tv_email.setTextColor(getResources().getColor(R.color.black));
            focusView = et_login_email;
            et_login_email.setError("Invaild Email");
            cancel = true;
        }

        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            if (focusView != null)
                focusView.requestFocus();
        }
        else
        {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

          //  isInternet = helper.isConnectingToInternet();

            if (ConnectivityReceiver.isConnected()) {
                makeForgotRequest(getemail);
            }
        }

    }

    private boolean isEmailValid(String email)
    {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    /**
     * Method to make json object request where json response starts wtih
     **/

    private void makeForgotRequest(String email)
    {

        // Tag used to cancel the request
        String tag_json_obj = "json_forgot_req";

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                BaseURL.RESET_PASSWORD, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    Boolean status = response.getBoolean("responce");
                    String error = response.getString("error");
                    String error_arb=response.getString("error_arb");
                    if (status) {
                        Toast.makeText(ResetPassword.this, "" + error, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ResetPassword.this, ResetPassword.class);
                        startActivity(i);
                        finish();

                    } else {
                        if (lan.contains("english")) {
                            Toast.makeText(ResetPassword.this, "" + error, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ResetPassword.this, "" + error_arb, Toast.LENGTH_SHORT).show();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(ResetPassword.this, getResources().getString(R.string.connection_time_out), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private class TAG {
    }
}