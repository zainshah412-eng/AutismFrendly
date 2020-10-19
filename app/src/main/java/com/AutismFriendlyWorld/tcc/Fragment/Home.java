package com.AutismFriendlyWorld.tcc.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.AutismFriendlyWorld.tcc.Adapter.Inspiration_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.My_Cities_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.Popular_Cities_Adapter;
import com.AutismFriendlyWorld.tcc.Fragment.City.CityDetail;
import com.AutismFriendlyWorld.tcc.Fragment.City.CityFragment;
import com.AutismFriendlyWorld.tcc.Model.Cities.City_ServerData;
import com.AutismFriendlyWorld.tcc.Model.Cities.My_Cities_Model;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.CityData;
import com.AutismFriendlyWorld.tcc.Model.Inspiration.Inspiration;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.City.Country_PlaceDetail;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Helper.RecyclerTouchListener;
import com.AutismFriendlyWorld.tcc.MainActivity;
import com.AutismFriendlyWorld.tcc.Model.My_order_model;
import com.AutismFriendlyWorld.tcc.NetworkConnectivity.ConnectionHelper;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.ConnectivityReceiver;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.AutismFriendlyWorld.tcc.util.GPSTracker;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends androidx.fragment.app.Fragment
{
    private static String TAG = Home.class.getSimpleName();


    EditText et_search;

//    RecyclerView citieslist;
//    LinearLayout featured_cover;
//    RelativeLayout no_order_layout_featured;
//    private List<My_Cities_Model> my_cities_models;



    RecyclerView inspiration_rec;
    LinearLayout inspiration_cover;
    RelativeLayout no_order_layout_inspiration;
    private List<Inspiration> inspirationList;
    TextView inspiration_viewall;


    RecyclerView popular_citieslist;
    LinearLayout popular_cities_cover;
    RelativeLayout no_popular_layout;
    private List<My_Cities_Model> my_popular_cities_models_list;
    TextView popular_view_all;

    RecyclerView newslist;
    LinearLayout news_cover;
    RelativeLayout no_order_layout_news;

    RelativeLayout relative_cover;

    SwipeRefreshLayout pullToRefresh;

    private List<My_order_model> my_order_modelList1;
    private Session_management sessionManagement;
    String get_id;
    KProgressHUD hud;
    private static final int PERMISSIONS_REQUEST_Location = 888;
    public static final String MyPREFERENCES = "MyPrefs" ;
    GoogleApiClient mGoogleApiClient;

    private String Longitude;
    private String Latitude;
    ConnectionHelper helper;
    Boolean isInternet;
    GPSTracker gps;
    android.app.AlertDialog alert;
    public static final int REQUEST_LOCATION = 1450;

    int pending_size=0;


    City_ServerData data_city;

    public Home() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);


  //      relative_cover = (RelativeLayout) view.findViewById(R.id.relative_cover);

        et_search = (EditText) view.findViewById(R.id.et_search);

        popular_citieslist = (RecyclerView) view.findViewById(R.id.popularlist);
        popular_cities_cover = (LinearLayout) view.findViewById(R.id.popular_cities_cover);
        no_popular_layout = (RelativeLayout) view.findViewById(R.id.no_popular_layout);
        popular_view_all = (TextView) view.findViewById(R.id.popular_view_all);

        LinearLayoutManager layoutManagerTop = new LinearLayoutManager(getActivity())
        {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getActivity()) {
                    private static final float SPEED = 2000f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }
                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }
        };

        layoutManagerTop.setOrientation(LinearLayoutManager.HORIZONTAL);
        popular_citieslist.setLayoutManager(layoutManagerTop);
        popular_citieslist.setHasFixedSize(true);
        popular_citieslist.setItemViewCacheSize(10);
        popular_citieslist.setDrawingCacheEnabled(true);
        popular_citieslist.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


        inspiration_rec = (RecyclerView) view.findViewById(R.id.inspirationlist);
        inspiration_cover = (LinearLayout) view.findViewById(R.id.inspiration_cover);
        no_order_layout_inspiration = (RelativeLayout) view.findViewById(R.id.no_order_layout_inspiration);
        inspiration_viewall = (TextView) view.findViewById(R.id.inspiration_viewall);


        LinearLayoutManager layoutManagerTop1 = new LinearLayoutManager(getActivity())
        {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getActivity()) {
                    private static final float SPEED = 2000f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }
                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }
        };
        layoutManagerTop1.setOrientation(LinearLayoutManager.HORIZONTAL);
        inspiration_rec.setLayoutManager(layoutManagerTop1);
        inspiration_rec.setHasFixedSize(true);
        inspiration_rec.setItemViewCacheSize(10);
        inspiration_rec.setDrawingCacheEnabled(true);
        inspiration_rec.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


//        popular_citieslist = (RecyclerView) view.findViewById(R.id.popular_citieslist);
//        popular_cities_cover = (LinearLayout) view.findViewById(R.id.popular_cities_cover);
//        no_order_layout_popular_cities = (RelativeLayout) view.findViewById(R.id.no_order_layout_popular_cities);
//
//
//        LinearLayoutManager layoutManagerTop2 = new LinearLayoutManager(getActivity())
//        {
//
//            @Override
//            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
//                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getActivity()) {
//                    private static final float SPEED = 2000f;// Change this value (default=25f)
//
//                    @Override
//                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
//                        return SPEED / displayMetrics.densityDpi;
//                    }
//                };
//                smoothScroller.setTargetPosition(position);
//                startSmoothScroll(smoothScroller);
//            }
//        };
//        layoutManagerTop2.setOrientation(LinearLayoutManager.HORIZONTAL);
//        popular_citieslist.setLayoutManager(layoutManagerTop2);
//        popular_citieslist.setHasFixedSize(true);
//        popular_citieslist.setItemViewCacheSize(10);
//        popular_citieslist.setDrawingCacheEnabled(true);
//        popular_citieslist.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
//
//
//        newslist = (RecyclerView) view.findViewById(R.id.newslist);
//        news_cover = (LinearLayout) view.findViewById(R.id.news_cover);
//        no_order_layout_news = (RelativeLayout) view.findViewById(R.id.no_order_layout_news);
//
//





        gps = new GPSTracker(getActivity());
        helper = new ConnectionHelper(getActivity());
        isInternet = helper.isConnectingToInternet();

        if (sessionManagement.isLoggedIn()) {
            String getname = sessionManagement.getUserDetails().get(BaseURL.KEY_NAME);
        }


        if (ConnectivityReceiver.isConnected())
        {
            makeInspritionRequest();
        } else {
            Toast.makeText(getActivity(), "Network Issue! Please try again", Toast.LENGTH_SHORT).show();
        }

        listener();

        return view;
    }



    void listener()
    {



        //Recycler View Cities
//        citieslist.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), citieslist, new RecyclerTouchListener.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(View view, int position)
//            {
//
//                String id = my_cities_models.get(position).getId();
//
//                androidx.fragment.app.Fragment fma1 = new CityDetail();
//                androidx.fragment.app.FragmentManager fragmentManager1 = getFragmentManager();
//                Bundle args = new Bundle();
//                args.putInt("id", Integer.parseInt(id));
//
//                fma1.setArguments(args);
//                String backStateName2 = fma1.getClass().getName();
//
//                FragmentManager manager2 = getActivity().getSupportFragmentManager();
//                boolean fragmentPopped2 = manager2.popBackStackImmediate (backStateName2, 0);
//
//                if (!fragmentPopped2)
//                { //fragment not in back stack, create it.
//                    FragmentTransaction ft = manager2.beginTransaction();
//                    ft.add(R.id.contentPanel, fma1);
//                    ft.addToBackStack(backStateName2);
//                    ft.commit();
//                }
//
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));

        //Recycler View Popular Cities


        popular_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                androidx.fragment.app.Fragment fm = new CityFragment();
                String backStateName3 = fm.getClass().getName();
                Bundle args = new Bundle();
                args.putInt("Calltype",1);

                FragmentManager manager3 = getActivity().getSupportFragmentManager();
                boolean fragmentPopped3 = manager3.popBackStackImmediate (backStateName3, 0);
                fm.setArguments(args);

                if (!fragmentPopped3)
                {
                    //fragment not in back stack, create it.
                    FragmentTransaction ft = manager3.beginTransaction();
                    ft.add(R.id.contentPanel, fm);
                    ft.addToBackStack(backStateName3);
                    ft.commit();
                }
            }
        });

        popular_citieslist.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), popular_citieslist, new RecyclerTouchListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                String id = my_popular_cities_models_list.get(position).getId();

                androidx.fragment.app.Fragment fma1 = new CityDetail();
                androidx.fragment.app.FragmentManager fragmentManager1 = getFragmentManager();
                Bundle args = new Bundle();
                args.putInt("id", Integer.parseInt(id));

                fma1.setArguments(args);
                String backStateName2 = fma1.getClass().getName();

                FragmentManager manager2 = getActivity().getSupportFragmentManager();
                boolean fragmentPopped2 = manager2.popBackStackImmediate (backStateName2, 0);

                if (!fragmentPopped2)
                { //fragment not in back stack, create it.
                    FragmentTransaction ft = manager2.beginTransaction();
                    ft.add(R.id.contentPanel, fma1);
                    ft.addToBackStack(backStateName2);
                    ft.commit();
                }

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        inspiration_viewall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                androidx.fragment.app.Fragment fm = new InspirationFragment();
                String backStateName3 = fm.getClass().getName();

                FragmentManager manager3 = getActivity().getSupportFragmentManager();
                boolean fragmentPopped3 = manager3.popBackStackImmediate (backStateName3, 0);

                if (!fragmentPopped3)
                {
                    //fragment not in back stack, create it.
                    FragmentTransaction ft = manager3.beginTransaction();
                    ft.add(R.id.contentPanel, fm);
                    ft.addToBackStack(backStateName3);
                    ft.commit();
                }
            }
        });
    }

//    public void makeCitiesRequest()
//    {
//
//        showPogressdialog();
//        String tag_json_obj = "json_socity_req";
//
//
//        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.GET,
//                BaseURL.CITIES, null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//
//
//
//                try
//                {
//
//                    String checkstatus = response.getString("code");
//                    String mesage = response.getString("message");
//
//
//
//                    if (checkstatus.contains("200"))
//                    {
//
//                        my_cities_models=new ArrayList<>();
//                        JSONArray jsonArray=response.getJSONArray("data");
//
//                        for (int i = 0; i < jsonArray.length(); i++)
//                        {
//
//                            JSONObject obj = jsonArray.getJSONObject(i);
//
//                            String id = obj.getString("id");
//                            String country_id = obj.getString("country_id");
//                            String name = obj.getString("name");
//                            String slug = obj.getString("slug");
//                            String intro = obj.getString("intro");
//                            String description = obj.getString("description");
//                            String thumb = obj.getString("thumb");
//                            String banner = obj.getString("banner");
//                            String best_time_to_visit = obj.getString("best_time_to_visit");
//                            String currency = obj.getString("currency");
//                            String language = obj.getString("language");
//                            String lat = obj.getString("lat");
//                            String lng = obj.getString("lng");
//                            String priority = obj.getString("priority");
//
//                            String status = obj.getString("status");
//                            String seo_title = obj.getString("seo_title");
//                            String seo_description = obj.getString("seo_description");
//                            String created_at = obj.getString("created_at");
//                            String updated_at = obj.getString("updated_at");
//                            String places_count = obj.getString("places_count");
//                            String country = obj.getString("country");
//
//
//
//                            My_Cities_Model my_order_model = new My_Cities_Model();
//                            my_order_model.setId(id);
//                            my_order_model.setCountry_id(country_id);
//                            my_order_model.setName(name);
//                            my_order_model.setSlug(slug);
//                            my_order_model.setIntro(intro);
//                            my_order_model.setDescription(description);
//                            my_order_model.setThumb(thumb);
//                            my_order_model.setBanner(banner);
//                            my_order_model.setBest_time_to_visit(best_time_to_visit);
//                            my_order_model.setCurrency(currency);
//                            my_order_model.setLanguage(language);
//                            my_order_model.setLat(lat);
//                            my_order_model.setLng(lng);
//                            my_order_model.setPriority(priority);
//                            my_order_model.setStatus(status);
//                            my_order_model.setSeo_title(seo_title);
//                            my_order_model.setSeo_description(seo_description);
//                            my_order_model.setCreated_at(created_at);
//                            my_order_model.setUpdated_at(updated_at);
//                            my_order_model.setPlaces_count(places_count);
//                            my_order_model.setCountry(country);
//
//                            if(i<4)
//                            {
//                                my_cities_models.add(my_order_model);
//                            }
//
//
//
//                        }
//                    }
//                    else
//                    {
//                        my_cities_models=new ArrayList<>();
//                    }
//
//
//
//                    My_Cities_Adapter myPendingOrderAdapter = new My_Cities_Adapter(my_cities_models,0);
//                    citieslist.setAdapter(myPendingOrderAdapter);
//                    myPendingOrderAdapter.notifyDataSetChanged();
//
//
//                    if (my_cities_models.isEmpty())
//                    {
//                        Toast.makeText(getActivity(), getResources().getString(R.string.no_pending_order), Toast.LENGTH_SHORT).show();
//                        citieslist.setVisibility(View.GONE);
//                        no_order_layout_featured.setVisibility(View.VISIBLE);
//                    }else {
//                        citieslist.setVisibility(View.VISIBLE);
//                        no_order_layout_featured.setVisibility(View.GONE);
//                    }
//
//
//
//                    makeInspritionRequest();
//
//                } catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if(hud.isShowing())
//                {
//                    if(hud.isShowing()){
//                        hud.dismiss();
//                    }
//                    hud.dismiss();
//                }
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                System.out.println("Error [" + error + "]");
//
//            }
//        });
//
//        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//
//    }

    public void makeInspritionRequest()
    {

        showPogressdialog();

        String tag_json_obj = "json_socity_req";

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.GET,
                BaseURL.INSPRITION, null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {

                try
                {

                    String checkstatus = response.getString("code");
                    String mesage = response.getString("message");



                    if (checkstatus.contains("200"))
                    {

                        inspirationList=new ArrayList<>();
                        JSONArray jsonArray=response.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++)
                        {

                            JSONObject obj = jsonArray.getJSONObject(i);

                            String id = obj.getString("id");
                            String user_id = obj.getString("user_id");
                            String title = obj.getString("title");
                            String slug = obj.getString("slug");
                            String content = obj.getString("content");
                            String thumb = obj.getString("thumb");
                            String type = obj.getString("type");
                            int status = obj.getInt("status");

                            String seo_title = obj.getString("seo_title");
                            String seo_description = obj.getString("seo_description");
                            String created_at = obj.getString("created_at");
                            String updated_at = obj.getString("updated_at");




                            Inspiration inspiration = new Inspiration();
                            inspiration.setId(id);
                            inspiration.setUser_id(user_id);
                            inspiration.setTitle(title);
                            inspiration.setSlug(slug);
                            inspiration.setContent(content);
                            inspiration.setThumb(thumb);
                            inspiration.setType(type);
                            inspiration.setStatus(status);
                            inspiration.setSeo_title(seo_title);
                            inspiration.setSeo_description(seo_description);
                            inspiration.setCreated_at(created_at);
                            inspiration.setUpdated_at(updated_at);


                            if(i<4)
                            {
                                inspirationList.add(inspiration);
                            }



                        }
                    }
                    else
                    {
                        inspirationList=new ArrayList<>();
                    }



                    Inspiration_Adapter myPendingOrderAdapter = new Inspiration_Adapter(inspirationList,0);
                    inspiration_rec.setAdapter(myPendingOrderAdapter);
                    myPendingOrderAdapter.notifyDataSetChanged();


                    if (inspirationList.isEmpty())
                    {
                        Toast.makeText(getActivity(), getResources().getString(R.string.no_pending_order), Toast.LENGTH_SHORT).show();
                        inspiration_rec.setVisibility(View.GONE);
                        no_order_layout_inspiration.setVisibility(View.VISIBLE);
                    }else {
                        inspiration_rec.setVisibility(View.VISIBLE);
                        no_order_layout_inspiration.setVisibility(View.GONE);
                    }

                    makePopularCitiesRequest();

                }
                catch (JSONException e)
                {
                    if(hud.isShowing()){
                        hud.dismiss();
                    }
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(hud.isShowing()){
                    hud.dismiss();
                }
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                System.out.println("Error [" + error + "]");

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    public void makePopularCitiesRequest()
    {

     //   showPogressdialog();
        String tag_json_obj = "json_socity_req";


        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.GET,
                BaseURL.POPULAR_CITIES, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try
                {
                    Gson gson = new Gson();
                    String checkstatus = response.getString("code");
                    String mesage = response.getString("message");



                    if (checkstatus.contains("200"))
                    {
                        JSONObject object=new JSONObject(String.valueOf(response));
                        Log.d("City Detail Url Data: ",String.valueOf(object));

                        data_city = gson.fromJson(String.valueOf(object), City_ServerData.class);

                        my_popular_cities_models_list=new ArrayList<>();

                        my_popular_cities_models_list = data_city.getMyCitiesModelList();

                    }
                    else
                    {
                        my_popular_cities_models_list=new ArrayList<>();
                    }



                    Popular_Cities_Adapter myPendingOrderAdapter = new Popular_Cities_Adapter(my_popular_cities_models_list,0);
                    popular_citieslist.setAdapter(myPendingOrderAdapter);
                    myPendingOrderAdapter.notifyDataSetChanged();


                    if (my_popular_cities_models_list.isEmpty())
                    {
                        Toast.makeText(getActivity(), getResources().getString(R.string.no_popular_cities), Toast.LENGTH_SHORT).show();
                        popular_citieslist.setVisibility(View.GONE);
                        no_popular_layout.setVisibility(View.VISIBLE);
                    }else {
                        popular_citieslist.setVisibility(View.VISIBLE);
                        no_popular_layout.setVisibility(View.GONE);
                    }

                    if(hud.isShowing()){
                        hud.dismiss();
                    }

         //           relative_cover.setAlpha(1);

                }
                catch (JSONException e)
                {
                    if(hud.isShowing()){
                        hud.dismiss();
                    }
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(hud.isShowing()){
                    hud.dismiss();
                }
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                System.out.println("Error [" + error + "]");

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    public void showPogressdialog(){
        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }





    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setBottomNavigationView(R.id.nav_home);
    }

}
