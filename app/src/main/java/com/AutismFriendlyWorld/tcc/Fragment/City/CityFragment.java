package com.AutismFriendlyWorld.tcc.Fragment.City;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.AutismFriendlyWorld.tcc.Adapter.Popular_Cities_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.Popular_Cities_Full_Screen_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.Popular_Cities_Full_Screen_Adapter;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Helper.RecyclerTouchListener;
import com.AutismFriendlyWorld.tcc.MainActivity;
import com.AutismFriendlyWorld.tcc.Model.Cities.City_ServerData;
import com.AutismFriendlyWorld.tcc.Model.Cities.My_Cities_Model;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.ConnectivityReceiver;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CityFragment extends Fragment {

    private static String TAG = CityFragment.class.getSimpleName();

    int calltype=0;

    City_ServerData data_city;
    private RelativeLayout no_order_layout;
    private LinearLayout no_order_layout_featured;
    private RecyclerView citieslist;
    private List<My_Cities_Model> my_cities_models;
    private Session_management sessionManagement;
    String get_id;
    int type;
    KProgressHUD hud;
    SwipeRefreshLayout pullToRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_city, container, false);
        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);

        calltype = getArguments().getInt("Calltype");


//        pullToRefresh = view.findViewById(R.id.pullToRefresh);
//        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh()
//            {
//                if (ConnectivityReceiver.isConnected())
//
//                {
//                    makeCitiesRequest();
//
//                }
//                else
//
//                {
//                    ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
//                }
//
//                //  refreshData(); // your code
//                pullToRefresh.setRefreshing(false);
//            }
//        });


        citieslist = (RecyclerView) view.findViewById(R.id.citieslist);
        no_order_layout = view.findViewById(R.id.no_order_layout);
        no_order_layout_featured = view.findViewById(R.id.no_order_layout_featured);



        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        citieslist.setLayoutManager(gridLayoutManager);
      //  citieslist.addItemDecoration(new GridSpacingItemDecoration(10, dpToPx(5), true));
        citieslist.setItemAnimator(new DefaultItemAnimator());
        citieslist.setNestedScrollingEnabled(false);
        citieslist.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));


//        citieslist.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (ConnectivityReceiver.isConnected())

        {

            if(calltype == 0)
            {
                makeCitiesRequest();
            }
            else {
                makePopularCitiesRequest();
            }

        } else

        {
            ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
        }


        initlistener();

        return view;

    }

    void initlistener()
    {

        //Recycler View Cities
        citieslist.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), citieslist, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {

                String id = my_cities_models.get(position).getId();

                androidx.fragment.app.Fragment fma1 = new CityDetail();
                androidx.fragment.app.FragmentManager fragmentManager1 = getFragmentManager();
                Bundle args = new Bundle();
                args.putInt("id", Integer.parseInt(id));

                fma1.setArguments(args);
                String backStateName2 = fma1.getClass().getName();

                FragmentManager manager2 = getActivity().getSupportFragmentManager();
                boolean fragmentPopped2 = manager2.popBackStackImmediate(backStateName2, 0);

                if (!fragmentPopped2) { //fragment not in back stack, create it.
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
    }


    public void makeCitiesRequest()
    {

        showPogressdialog();
        String tag_json_obj = "json_socity_req";


        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.GET,
                BaseURL.CITIES, null, new Response.Listener<JSONObject>() {

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

                        my_cities_models=new ArrayList<>();

                        my_cities_models = data_city.getMyCitiesModelList();

                    }
                    else
                    {
                        my_cities_models=new ArrayList<>();
                    }



                    Popular_Cities_Full_Screen_Adapter myPendingOrderAdapter = new Popular_Cities_Full_Screen_Adapter(my_cities_models,0);
                    citieslist.setAdapter(myPendingOrderAdapter);
                    myPendingOrderAdapter.notifyDataSetChanged();


                    if (my_cities_models.isEmpty())
                    {
                        Toast.makeText(getActivity(), getResources().getString(R.string.no_pending_order), Toast.LENGTH_SHORT).show();
                        citieslist.setVisibility(View.GONE);
                        no_order_layout.setVisibility(View.VISIBLE);
                    }else {
                        citieslist.setVisibility(View.VISIBLE);
                        no_order_layout.setVisibility(View.GONE);
                    }

                    if(hud.isShowing())
                    {
                        hud.dismiss();
                    }
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
                if(hud.isShowing())
                {
                    if(hud.isShowing()){
                        hud.dismiss();
                    }
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

        showPogressdialog();
        String tag_json_obj = "json_socity_req";


        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.GET,
                BaseURL.CITIES, null, new Response.Listener<JSONObject>() {

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

                        my_cities_models=new ArrayList<>();

                        my_cities_models = data_city.getMyCitiesModelList();

                    }
                    else
                    {
                        my_cities_models=new ArrayList<>();
                    }



                    Popular_Cities_Full_Screen_Adapter myPendingOrderAdapter = new Popular_Cities_Full_Screen_Adapter(my_cities_models,0);
                    citieslist.setAdapter(myPendingOrderAdapter);
                    myPendingOrderAdapter.notifyDataSetChanged();


                    if (my_cities_models.isEmpty())
                    {
                        Toast.makeText(getActivity(), getResources().getString(R.string.no_pending_order), Toast.LENGTH_SHORT).show();
                        citieslist.setVisibility(View.GONE);
                        no_order_layout.setVisibility(View.VISIBLE);
                    }else {
                        citieslist.setVisibility(View.VISIBLE);
                        no_order_layout.setVisibility(View.GONE);
                    }

                    if(hud.isShowing())
                    {
                        hud.dismiss();
                    }
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
                if(hud.isShowing())
                {
                    if(hud.isShowing()){
                        hud.dismiss();
                    }
                    hud.dismiss();
                }
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        //Defining retrofit api service

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp)
    {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}