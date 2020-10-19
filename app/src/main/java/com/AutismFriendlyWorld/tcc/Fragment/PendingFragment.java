package com.AutismFriendlyWorld.tcc.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Helper.RecyclerTouchListener;
import com.AutismFriendlyWorld.tcc.MainActivity;
import com.AutismFriendlyWorld.tcc.Model.My_order_model;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.ConnectivityReceiver;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PendingFragment extends Fragment
{


    private RelativeLayout no_order_layout;
    private LinearLayout ordersLayout;
    private RecyclerView rv_myorder;
    private List<My_order_model> my_order_modelList;
    private Session_management sessionManagement;
    String get_id;
    int type;
    KProgressHUD hud;

    public PendingFragment() {
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

        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);




        rv_myorder = (RecyclerView) view.findViewById(R.id.rv_myorder);
        no_order_layout = view.findViewById(R.id.no_order_layout);
        ordersLayout = view.findViewById(R.id.orders_layout);
        rv_myorder.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (ConnectivityReceiver.isConnected())

        {

                // makeGetPendingOrderRequest();


        } else

        {
            ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
        }


        initlistener();

        return view;

    }

    void initlistener()
    {
        //Recycler View Deal Of Day
        rv_myorder.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_myorder, new RecyclerTouchListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

//                String saleid = my_order_modelList.get(position).getSale_id();
//                String placedon = my_order_modelList.get(position).getOn_date();
//                String time = my_order_modelList.get(position).getDelivery_time_from() + "-" + my_order_modelList.get(position).getDelivery_time_to();
//                String item = my_order_modelList.get(position).getTotal_items();
//                String ammount = my_order_modelList.get(position).getTotal_amount();
//                String status = my_order_modelList.get(position).getStatus();
//                String society=my_order_modelList.get(position).getSocityname();
//                String house=my_order_modelList.get(position).getHouse();
//                String recivername=my_order_modelList.get(position).getRecivername();
//                String recivermobile=my_order_modelList.get(position).getRecivermobile();
//                String delivery_charges=my_order_modelList.get(position).getDelivery_charge();
//                String latitude=my_order_modelList.get(position).getLatitude();
//                String longitude=my_order_modelList.get(position).getLongitude();
//
//                String discount=my_order_modelList.get(position).getDiscount();
//                String subtotal=my_order_modelList.get(position).getSubtotal();
//
//                androidx.fragment.app.Fragment fma1 = new OrderDetail();
//                androidx.fragment.app.FragmentManager fragmentManager1 = getFragmentManager();
//                Bundle args = new Bundle();
//                args.putInt("Calltype",type);
//                args.putString("sale_id", saleid);
//                args.putString("placedon", placedon);
//                args.putString("time", time);
//                args.putString("item", item);
//                args.putString("ammount", ammount);
//                args.putString("status", status);
//                args.putString("socity_name",society);
//                args.putString("house_no",house);
//                args.putString("receiver_name",recivername);
//                args.putString("receiver_mobile",recivermobile);
//                args.putString("delivery_charges",delivery_charges);
//                args.putString("latitude",latitude);
//                args.putString("longitude",longitude);
//
//                args.putString("discount",discount);
//                args.putString("subtotal",subtotal);
//                fma1.setArguments(args);
//
//                String backStateName2 = fma1.getClass().getName();
//
//                FragmentManager manager2 = getActivity().getSupportFragmentManager();
//                boolean fragmentPopped2 = manager2.popBackStackImmediate (backStateName2, 0);
//
//                if (!fragmentPopped2)
//                {
//
//                    FragmentTransaction ft = manager2.beginTransaction();
//                    ft.replace(R.id.contentPanel, fma1);
//                    ft.addToBackStack(backStateName2);
//                    ft.commit();
//
//                }


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

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

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setBottomNavigationView(R.id.nav_search);
    }
}