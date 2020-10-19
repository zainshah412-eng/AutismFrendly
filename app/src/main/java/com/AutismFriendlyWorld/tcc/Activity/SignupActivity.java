package com.AutismFriendlyWorld.tcc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.AutismFriendlyWorld.tcc.R;

import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity
{

    RelativeLayout btn_Signup_phone_no;
    RelativeLayout fb_login_btn;
    RelativeLayout btn_google_sign_in;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_Signup_phone_no = (RelativeLayout) findViewById(R.id.btn_google_sign_in);
        fb_login_btn = (RelativeLayout) findViewById(R.id.fb_login_btn);
        btn_google_sign_in = (RelativeLayout) findViewById(R.id.btn_google_sign_in);
        login = (TextView) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LogInActivity.class));
            }
        });
    }

}