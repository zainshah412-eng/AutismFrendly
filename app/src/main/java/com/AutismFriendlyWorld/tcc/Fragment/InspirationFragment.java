package com.AutismFriendlyWorld.tcc.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.AutismFriendlyWorld.tcc.Adapter.Inspiration_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.Inspiration_Full_Screen_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.My_Cities_Adapter;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Helper.RecyclerTouchListener;
import com.AutismFriendlyWorld.tcc.MainActivity;
import com.AutismFriendlyWorld.tcc.Model.Cities.My_Cities_Model;
import com.AutismFriendlyWorld.tcc.Model.Inspiration.Inspiration;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.ConnectivityReceiver;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class InspirationFragment extends Fragment {

    private static String TAG = InspirationFragment.class.getSimpleName();

    private LinearLayout no_order_layout;
    private RelativeLayout no_order_layout_inspiration;
    private RecyclerView inspirationlist;
    private List<Inspiration> inspirationList;
    private Session_management sessionManagement;
    String get_id;
    int type;
    KProgressHUD hud;
    SwipeRefreshLayout pullToRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_inspiration, container, false);
        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);


        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                if (ConnectivityReceiver.isConnected())

                {
                    makeInspritionRequest();

                }
                else

                {
                    ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
                }

                //  refreshData(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });


        inspirationlist = (RecyclerView) view.findViewById(R.id.inspirationlist);
        no_order_layout = view.findViewById(R.id.no_order_layout);
        no_order_layout_inspiration = view.findViewById(R.id.no_order_layout_inspiration);
        inspirationlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (ConnectivityReceiver.isConnected())

        {

            makeInspritionRequest();


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
        inspirationlist.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), inspirationlist, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {

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
//                boolean fragmentPopped2 = manager2.popBackStackImmediate(backStateName2, 0);
//
//                if (!fragmentPopped2) { //fragment not in back stack, create it.
//                    FragmentTransaction ft = manager2.beginTransaction();
//                    ft.add(R.id.contentPanel, fma1);
//                    ft.addToBackStack(backStateName2);
//                    ft.commit();
//                }

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


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


                            inspirationList.add(inspiration);




                        }
                    }
                    else
                    {
                        inspirationList=new ArrayList<>();
                    }



                    Inspiration_Full_Screen_Adapter myPendingOrderAdapter = new Inspiration_Full_Screen_Adapter(inspirationList,0);
                    inspirationlist.setAdapter(myPendingOrderAdapter);
                    myPendingOrderAdapter.notifyDataSetChanged();


                    if (inspirationList.isEmpty())
                    {
                        Toast.makeText(getActivity(), getResources().getString(R.string.no_pending_order), Toast.LENGTH_SHORT).show();
                        no_order_layout.setVisibility(View.GONE);
                        no_order_layout_inspiration.setVisibility(View.VISIBLE);
                    }else {
                        no_order_layout.setVisibility(View.VISIBLE);
                        no_order_layout_inspiration.setVisibility(View.GONE);
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