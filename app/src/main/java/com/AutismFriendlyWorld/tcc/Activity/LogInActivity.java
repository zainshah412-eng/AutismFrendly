package com.AutismFriendlyWorld.tcc.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.franmontiel.localechanger.LocaleChanger;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.MainActivity;
import com.AutismFriendlyWorld.tcc.NetworkConnectivity.ConnectionHelper;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.fcm.MyFirebaseRegister;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.AutismFriendlyWorld.tcc.Config.BaseURL.IS_LOGIN;

public class LogInActivity extends AppCompatActivity
{
    EditText Et_login_email;
    EditText Et_login_password;
    RelativeLayout Btn_Sign_in;
    RelativeLayout Btn_Sign_up;
    TextView tv_login_email;
    TextView txt_forgot_password;
    String getemail;
    KProgressHUD hud;
    CheckBox check_box_remember_me;
    public static final String MyPREFERENCES = "MyPrefs" ;
    ConnectionHelper helper;
    Boolean isInternet;
    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);

        helper = new ConnectionHelper(this);
        isInternet = helper.isConnectingToInternet();
//        String token1 = FirebaseInstanceId.getInstance().getToken();
//        String token = SharedPref.getString(LogInActivity.this,SharedPrefManager.getInstance(LogInActivity.this).getDeviceToken());
        Et_login_email = (EditText) findViewById(R.id.et_login_email);
        Et_login_password = (EditText) findViewById(R.id.et_login_password);
        tv_login_email = (TextView) findViewById(R.id.tv_login_email);
        txt_forgot_password = (TextView) findViewById(R.id.txt_forgot_password);
        Btn_Sign_in = (RelativeLayout) findViewById(R.id.btn_Sign_in);
        Btn_Sign_up = (RelativeLayout) findViewById(R.id.btn_Sign_up);
        check_box_remember_me = (CheckBox) findViewById(R.id.check_box_remember_me);

        getemail = Et_login_email.getText().toString();

        Btn_Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LogInActivity.this,MainActivity.class));


//                isInternet = helper.isConnectingToInternet();
//                if(!Et_login_email.equals("") && !Et_login_password.equals(""))
//                {
//                    if(isInternet)
//                    {
//                        makejson();
//                    }else{
//                        Toast.makeText(LogInActivity.this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
//                    }
//                }
//               else if (Et_login_email.equals(""))
//                {
//                    Toast.makeText(LogInActivity.this, "Please Put Your Correct Email-Id", Toast.LENGTH_SHORT).show();
//                }
//                else if (Et_login_password.equals("")) {
//                    Toast.makeText(LogInActivity.this, "Please Put Your Correct Password", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//
//                }

            }
        });

        Btn_Sign_up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LogInActivity.this,SignupActivity.class));
            }
        });

        txt_forgot_password.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LogInActivity.this,ResetPassword.class));
            }
        });

    }



    public void makejson()
    {

        showPogressdialog();
        String tag_json_obj = "json_login_req";

        String UserName = Et_login_email.getText().toString().trim();
        String UserPassword = Et_login_password.getText().toString().trim();


        Map<String, String> params = new HashMap<String, String>();
        params.put("email", UserName);
        params.put("password", UserPassword);



        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                BaseURL.LOGIN, params, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {

                if(hud.isShowing()){
                    hud.dismiss();
                }

                try
                {

                    String status = response.getString("code");


                    if (status.contains("200"))
                    {

                        JSONObject jsonObject=response.getJSONObject("data");

                        String user_id = jsonObject.getString("id");
                        String user_fullname = jsonObject.getString("user_name");
                        String user_email = jsonObject.getString("user_email");
                        String user_password = jsonObject.getString("user_password");
                        String user_phone = jsonObject.getString("user_phone");
                        String image = jsonObject.getString("image");
                        String user_latitude = jsonObject.getString("user_latitude");
                        String user_longitude = jsonObject.getString("user_longitude");
                        String vehicle_type = jsonObject.getString("vehicle_type");
                        String vehicle_number = jsonObject.getString("vehicle_number");
                        String vehicle_model = jsonObject.getString("vehicle_model");
                        String vehicle_color = jsonObject.getString("vehicle_color");
                        String user_gcm_code = jsonObject.getString("user_gcm_code");
                        String user_ios_token = jsonObject.getString("user_ios_token");
                        String user_status = jsonObject.getString("user_status");



                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("user_id", user_id);
                        editor.commit();

                        if (check_box_remember_me.isChecked())
                        {
                            editor.putBoolean(IS_LOGIN, true);
                        }
                        else
                        {
                            editor.putBoolean(IS_LOGIN, false);
                        }

                        Session_management sessionManagement = new Session_management(LogInActivity.this);
                        sessionManagement.createLoginSession(user_id, user_fullname,user_email,user_password,user_phone,image,
                                user_latitude,user_longitude,vehicle_type, vehicle_number,vehicle_model,
                                vehicle_color,user_gcm_code,user_ios_token,user_status);
                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                        MyFirebaseRegister myFirebaseRegister=new MyFirebaseRegister(LogInActivity.this);
                        myFirebaseRegister.RegisterUser(user_id);

                        Btn_Sign_in.setEnabled(false);
                    }
                    else
                        {
                        Btn_Sign_in.setEnabled(true);

                        Toast.makeText(LogInActivity.this, response.getString("data"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {


            @Override
            public void onErrorResponse(VolleyError error)
            {
                if(hud.isShowing()){
                    hud.dismiss();
                }

                System.out.println("Error [" + error + "]");

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    public void showPogressdialog()
    {
        hud = KProgressHUD.create(LogInActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }
}