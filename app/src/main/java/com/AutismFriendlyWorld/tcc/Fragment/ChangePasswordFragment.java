package com.AutismFriendlyWorld.tcc.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.NetworkConnectivity.ConnectionHelper;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ChangePasswordFragment extends Fragment
{

    KProgressHUD hud;
    ConnectionHelper helper;
    Boolean isInternet;


    private Session_management sessionManagement;
    String get_id;

    EditText et_old_password,et_new_password,et_confirm_password;
    RelativeLayout btn_Submit;



    public ChangePasswordFragment()
    {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_change_password,
                container, false);

        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);


        helper = new ConnectionHelper(getActivity());
        isInternet = helper.isConnectingToInternet();


        et_old_password = (EditText) view.findViewById(R.id.et_old_password);
        et_new_password = (EditText) view.findViewById(R.id.et_new_password);
        et_confirm_password = (EditText) view.findViewById(R.id.et_confirm_password);

        btn_Submit = (RelativeLayout) view.findViewById(R.id.btn_Submit);


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isInternet = helper.isConnectingToInternet();
                if(!et_old_password.equals("") && !et_new_password.equals("")&& !et_confirm_password.equals(""))
                {
                    if(et_new_password.getText().toString() .equalsIgnoreCase(et_confirm_password.getText().toString()))
                    {
                        if(isInternet)
                        {
                            makejson();
                            btn_Submit.setEnabled(false);
                        }else{
                            Toast.makeText(getActivity(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                    }

                }
                else if (et_old_password.equals(""))
                {
                    Toast.makeText(getActivity(), "Please Put Your Correct Password", Toast.LENGTH_SHORT).show();
                }
                else if (et_new_password.equals(""))
                {
                    Toast.makeText(getActivity(), "Please Put Your Correct New Password", Toast.LENGTH_SHORT).show();
                }
                else if (et_confirm_password.equals(""))
                {
                    Toast.makeText(getActivity(), "Please Put Your Correct Old Password", Toast.LENGTH_SHORT).show();
                }
                else {

                }
            }
        });

        return view;
    }


    public void makejson() {

        showPogressdialog();
        String tag_json_obj = "json_login_req";

        String userold_password = et_old_password.getText().toString().trim();
        String usernew_password = et_new_password.getText().toString().trim();
        String userconfirm_password = et_confirm_password.getText().toString().trim();


        Map<String, String> params = new HashMap<String, String>();
        params.put("id", get_id);
        params.put("current_password", userold_password);
        params.put("new_password", userconfirm_password);



        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                BaseURL.CHNAGE_PASSWORD, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                if(hud.isShowing()){
                    hud.dismiss();
                }

                try
                {

                    String status = response.getString("responce");


                    if (status.contains("true"))
                    {

                        Toast.makeText(getActivity(), response.getString("data"), Toast.LENGTH_LONG).show();
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStackImmediate();

                    }
                    else
                    {
                        btn_Submit.setEnabled(true);

                        Toast.makeText(getActivity(), response.getString("data"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
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
        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

}