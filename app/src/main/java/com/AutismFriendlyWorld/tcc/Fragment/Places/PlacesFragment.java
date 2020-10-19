package com.AutismFriendlyWorld.tcc.Fragment.Places;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.Places_Adapter;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Helper.RecyclerTouchListener;
import com.AutismFriendlyWorld.tcc.MainActivity;
import com.AutismFriendlyWorld.tcc.Model.Places.PlaceModel;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesData;
import com.AutismFriendlyWorld.tcc.Model.Places.Places_City_Data;
import com.AutismFriendlyWorld.tcc.Model.Places.Places_Translation;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.ConnectivityReceiver;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class PlacesFragment extends Fragment
{


    private static String TAG = PlacesFragment.class.getSimpleName();

    private LinearLayout no_places_layout;
    private RelativeLayout no_places_layout_places;
    private RecyclerView placeslist_rec;
    private List<PlaceModel> placesList;
    private Places_City_Data places_city_data;
    private List<Places_Translation> places_translationList;





    PlacesData data;

    private Session_management sessionManagement;
    String get_id;
    int type;
    KProgressHUD hud;
    SwipeRefreshLayout pullToRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_places, container, false);
        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);


        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                if (ConnectivityReceiver.isConnected())

                {
                    makeplacesRequest();

                }
                else

                {
                    ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
                }

                //  refreshData(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });


        placeslist_rec = (RecyclerView) view.findViewById(R.id.placeslist);
        no_places_layout = view.findViewById(R.id.no_places_layout);
        no_places_layout_places = view.findViewById(R.id.no_places_layout_places);



        placeslist_rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (ConnectivityReceiver.isConnected())

        {

            makeplacesRequest();


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
        placeslist_rec.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), placeslist_rec, new RecyclerTouchListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {



                //
                int id = placesList.get(position).getId();

                androidx.fragment.app.Fragment fma1 = new PlacesDetailFragment();
                androidx.fragment.app.FragmentManager fragmentManager1 = getFragmentManager();
                Bundle args = new Bundle();
                args.putInt("id", id);

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


    public void makeplacesRequest()
    {

        showPogressdialog();

        String tag_json_obj = "json_socity_req";

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.GET,
                BaseURL.PLACES, null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {

                try
                {
                    Gson gson = new Gson();
                    String checkstatus = response.getString("code");
                    String mesage = response.getString("message");




                    if (checkstatus.contains("200"))
                    {

                        JSONObject object=new JSONObject(String.valueOf(response.getJSONObject("data")));

                        Log.d("City Detail Url Data: ",String.valueOf(object));

                        data = gson.fromJson(String.valueOf(object), PlacesData.class);

                        placesList = new ArrayList<>();
                        placesList = data.getPlaceModelList();




                    }
                    else
                    {
                        placesList=new ArrayList<>();
                    }



                    Places_Adapter places_adapter = new Places_Adapter(placesList,0);
                    placeslist_rec.setAdapter(places_adapter);
                    places_adapter.notifyDataSetChanged();


                    if (placesList.isEmpty())
                    {
                        Toast.makeText(getActivity(), getResources().getString(R.string.no_pending_order), Toast.LENGTH_SHORT).show();
                        no_places_layout.setVisibility(View.GONE);
                        no_places_layout_places.setVisibility(View.VISIBLE);
                    }else {
                        no_places_layout.setVisibility(View.VISIBLE);
                        no_places_layout_places.setVisibility(View.GONE);
                    }

                    if(hud.isShowing()){
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
                if(hud.isShowing()){
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
}