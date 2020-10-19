package com.AutismFriendlyWorld.tcc.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Config.JSONParser;
import com.AutismFriendlyWorld.tcc.MainActivity;
import com.AutismFriendlyWorld.tcc.NetworkConnectivity.ConnectionHelper;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.ConnectivityReceiver;
import com.AutismFriendlyWorld.tcc.util.GPSTracker;
import com.AutismFriendlyWorld.tcc.util.NameValuePair;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.AutismFriendlyWorld.tcc.AppController.TAG;


public class ProfileFragment extends Fragment
{

    ConnectionHelper helper;
    Boolean isInternet;
    GPSTracker gps;
    private Session_management sessionManagement;
    String get_id;
    String get_username;
    String get_usernumber;
    String get_useremail;
    String get_userpassword;
    String get_userimage;
    String success_msg;
    KProgressHUD hud;

    private static final int GALLERY_REQUEST_CODE1 = 201;
    SharedPreferences myPrefrence;
    private Bitmap bitmap;
    private String filePath = "";


    ImageView iv_pro_img;
    TextView et_username,et_number,et_email,et_password;
    RelativeLayout btn_Sign_in;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_profile,
                container, false);
        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);
        get_username = sessionManagement.getUserDetails().get(BaseURL.KEY_NAME);
        get_usernumber = sessionManagement.getUserDetails().get(BaseURL.KEY_PHONE_NO);
        get_useremail = sessionManagement.getUserDetails().get(BaseURL.KEY_EMAIL);
        get_userpassword = sessionManagement.getUserDetails().get(BaseURL.KEY_PASSWORD);
        get_userimage = sessionManagement.getUserDetails().get(BaseURL.KEY_IMAGE);


        iv_pro_img = (ImageView) view.findViewById(R.id.iv_pro_img);
        et_username = (TextView) view.findViewById(R.id.et_username);
        et_number = (TextView) view.findViewById(R.id.et_number);
        et_email = (TextView) view.findViewById(R.id.et_email);
        et_password = (TextView) view.findViewById(R.id.et_password);
        btn_Sign_in = (RelativeLayout) view.findViewById(R.id.btn_Sign_in);

        initListener();
        initData();

        return view;
    }
    void initListener()
    {
        iv_pro_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE1);
            }
        });
        btn_Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptEditProfile();
                if(bitmap!=null){
                    storeImage(bitmap);
                }
            }
        });
        et_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.fragment.app.Fragment fma2 = new ChangePasswordFragment();
                String backStateName2 = fma2.getClass().getName();

                FragmentManager manager2 = getActivity().getSupportFragmentManager();
                boolean fragmentPopped2 = manager2.popBackStackImmediate (backStateName2, 0);

                if (!fragmentPopped2) { //fragment not in back stack, create it.
                    FragmentTransaction ft = manager2.beginTransaction();
                    ft.replace(R.id.contentPanel, fma2);
                    ft.addToBackStack(backStateName2);
                    ft.commit();
                }
            }
        });
    }
    void initData()
    {
        et_username.setText(get_username);
        et_number.setText(get_usernumber);
        et_email.setText(get_useremail);
        et_password.setText(get_userpassword);

        try {



            Glide.with(getActivity())
                    .load(BaseURL.IMG_USER_URL + get_userimage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(iv_pro_img);
        }
        catch (Exception e)
        {

        }

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

    private void storeImage(Bitmap thumbnail)
    {
        if (iv_pro_img.getDrawable() == null)
        {
            //  Toast.makeText(getActivity(), "Select Image", Toast.LENGTH_SHORT).show();

        } else {
            myPrefrence = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor edit = myPrefrence.edit();
            edit.remove("image_data");
            edit.commit();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            File destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            iv_pro_img.setImageBitmap(thumbnail);
            byte[] b = bytes.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            edit.putString("image_data", encodedImage);
            edit.commit();

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // if the result is capturing Image
        if ((requestCode == GALLERY_REQUEST_CODE1))
        {
            if (resultCode == getActivity().RESULT_OK)
            {
                try {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    // Get the cursor
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();

                    //filePath = imgDecodableString;

                    Bitmap b = BitmapFactory.decodeFile(imgDecodableString);
                    Bitmap out = Bitmap.createScaledBitmap(b, 1200, 1024, false);

                    //getting image from gallery
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);


                    File file = new File(imgDecodableString);
                    filePath = file.getAbsolutePath();
                    FileOutputStream fOut;
                    try {
                        fOut = new FileOutputStream(file);
                        out.compress(Bitmap.CompressFormat.PNG, 80, fOut);
                        fOut.flush();
                        fOut.close();
                        //b.recycle();
                        //out.recycle();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (requestCode == GALLERY_REQUEST_CODE1)
                    {

                        // Set the Image in ImageView after decoding the String
                        iv_pro_img.setImageBitmap(bitmap);
                    }
                } catch (NullPointerException e)
                {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void attemptEditProfile()
    {

        boolean cancel = false;
        View focusView = null;




        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            if (focusView != null)
                focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            if (ConnectivityReceiver.isConnected()) {

                String user_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);

                // check internet connection
                if (ConnectivityReceiver.isConnected()) {
                    new editProfile(user_id).execute();
                }
            }
        }
    }

    // asynctask for upload data with image or not image using HttpOk
    public class editProfile extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog progressDialog;
        JSONParser jsonParser;
        ArrayList<NameValuePair> nameValuePairs;
        boolean response;
        String error_string, success_msg;


        String getimage;


        public editProfile(String user_id) {
            nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new NameValuePair("id", user_id));


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

       //     progressDialog = ProgressDialog.show(getActivity(), "", getResources().getString(R.string.uploading_profile_data), true);
            showPogressdialog();

            jsonParser = new JSONParser(getActivity());
        }

        @Override
        protected Void doInBackground(Void... params)
        {

            String json_responce = null;
            try {
                if (filePath == "") {
                    json_responce = jsonParser.execPostScriptJSON(BaseURL.UPLOAD_IMAGE, nameValuePairs);
                } else {
                    json_responce = jsonParser.execMultiPartPostScriptJSON(BaseURL.UPLOAD_IMAGE, nameValuePairs, "image/png", filePath, "image");
                }
                Log.e(TAG, json_responce + "," + filePath);

                JSONObject jObj = new JSONObject(json_responce);
                if (jObj.getBoolean("responce"))
                {
                    response = true;
                    getimage = jObj.getString("data");

                }
                else
                    {
                    response = false;
                    getimage = jObj.getString("data");
                    error_string = jObj.getString("data");
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            if (progressDialog != null) {
//                progressDialog.hide();
//                progressDialog.dismiss();
//                progressDialog = null;
//            }
            if (hud != null) {
              //  hud.hide();
                hud.dismiss();
                hud = null;
            }

            if (response)
            {

                sessionManagement.updateData(getimage);

                ((MainActivity) getActivity()).updateHeader();

                Toast.makeText(getActivity(), getResources().getString(R.string.profile_updated), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);

            }
            else
            {
                Toast.makeText(getActivity(), "" + error_string, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if (hud != null) {
             //   progressDialog.hide();
                hud.dismiss();
                hud = null;
            }
//            if (progressDialog != null) {
//                progressDialog.hide();
//                progressDialog.dismiss();
//                progressDialog = null;
//            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setBottomNavigationView(R.id.nav_profile);
    }
}