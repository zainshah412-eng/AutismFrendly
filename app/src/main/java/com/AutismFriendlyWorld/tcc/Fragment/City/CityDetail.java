package com.AutismFriendlyWorld.tcc.Fragment.City;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.AutismFriendlyWorld.tcc.Adapter.Category_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.CityData_Adapter.CityData_Amenity_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.CityData_Adapter.CityData_PlacesType_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.CityData_Adapter.CityDetail_Home_CategoryList;
import com.AutismFriendlyWorld.tcc.Adapter.CityData_Adapter.CityDetail_OtherCity_Adapter;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Fragment.Places.MapsFragment;
import com.AutismFriendlyWorld.tcc.Helper.RecyclerTouchListener;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.Amenities.Amenity;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.Category.Category_CityDetailModel;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.City.City;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.CityData;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.Features.Feature;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.OtherCities.OtherCity;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.PlacesByCategory.PlacesByCategory;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.place_Types.Place_type;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place_PlaceDetail;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.ConnectivityReceiver;
import com.AutismFriendlyWorld.tcc.util.CustomVolleyJsonRequest;
import com.AutismFriendlyWorld.tcc.util.Session_management;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CityDetail extends Fragment
{

    private static String TAG = CityDetail.class.getSimpleName();
    private Session_management sessionManagement;
    String get_id;
    KProgressHUD hud;
    int id;

    RelativeLayout cover;

    ImageView imageview;
    TextView place_name;
    TextView title;
    TextView description;

    LinearLayout cover_currency;
    TextView tv_currency;

    LinearLayout cover_language;
    TextView tv_lagnuage;

    LinearLayout cover_visit_time;
    TextView tv_best_time_visit;

    TextView tv_created_at;
    TextView tv_updated_at;
    TextView start_navigation;

    LinearLayout home_cover;
    LinearLayout category_type_cover;
    TextView header_name;
    RelativeLayout no_places_layout_places;


    RecyclerView category_recycler;
    List<Category_CityDetailModel> category_cityDetailModels;


    RecyclerView othercities_recycler;
    List<OtherCity> otherCityList;


    RecyclerView amenity_recycler;
    List<Amenity> amenityList;


    RecyclerView categoryhome_list_recycler;
    List<Category_CityDetailModel> category_cityDetailModelList;
    List<Feature> feature_detail_List;





    City citymodel;

    Category_Adapter cityDetail_category_adapter;
    RecyclerView category_type_recycler;
    List<Feature> featureList;
    List<Place_PlaceDetail> place_placeDetailList;


    List<PlacesByCategory> placesByCategoryList;
    List<Place_type> place_typeList;



    CityData data;

    String latitude="";
    String longitude="";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_detail, container, false);

        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);


        id = getArguments().getInt("id");
     //   id = 31;



 //       cover =  view.findViewById(R.id.cover);
        imageview =  view.findViewById(R.id.imageview);
        place_name =  view.findViewById(R.id.place_name);
        title =  view.findViewById(R.id.title);
        description =  view.findViewById(R.id.description);

        cover_currency =  view.findViewById(R.id.cover_currency);
        tv_currency =  view.findViewById(R.id.tv_currency);

        tv_lagnuage =  view.findViewById(R.id.tv_lagnuage);
        cover_language =  view.findViewById(R.id.cover_language);

        tv_best_time_visit =  view.findViewById(R.id.tv_best_time_visit);
        cover_visit_time =  view.findViewById(R.id.cover_visit_time);

        tv_created_at =  view.findViewById(R.id.tv_created_at);
        tv_updated_at =  view.findViewById(R.id.tv_updated_at);
        start_navigation =  view.findViewById(R.id.start_navigation);



        start_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + "Autism Friendly" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                CityDetail.this.startActivity(intent);
            }
        });

        home_cover = view.findViewById(R.id.home_cover);
        category_type_cover = view.findViewById(R.id.category_type_cover);

        header_name = view.findViewById(R.id.header_name);
        category_type_recycler = view.findViewById(R.id.category_type_recycler);
        no_places_layout_places = view.findViewById(R.id.no_places_layout_places);

        categoryhome_list_recycler = view.findViewById(R.id.categoryhome_list_recycler);





        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        category_type_recycler.setLayoutManager(gridLayoutManager);
        //  citieslist.addItemDecoration(new GridSpacingItemDecoration(10, dpToPx(5), true));
        category_type_recycler.setItemAnimator(new DefaultItemAnimator());
        category_type_recycler.setNestedScrollingEnabled(false);
        category_type_recycler.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));


        //TODO: Category
        category_recycler =  view.findViewById(R.id.category_recycler);
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
        category_recycler.setLayoutManager(layoutManagerTop1);
        category_recycler.setHasFixedSize(true);
        category_recycler.setItemViewCacheSize(10);
        category_recycler.setDrawingCacheEnabled(true);
        category_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


        //TODO: OTHER CITIES
        othercities_recycler =  view.findViewById(R.id.othercities_recycler);
        LinearLayoutManager layoutManagerTop2 = new LinearLayoutManager(getActivity())
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
        layoutManagerTop2.setOrientation(LinearLayoutManager.HORIZONTAL);
        othercities_recycler.setLayoutManager(layoutManagerTop2);
        othercities_recycler.setHasFixedSize(true);
        othercities_recycler.setItemViewCacheSize(10);
        othercities_recycler.setDrawingCacheEnabled(true);
        othercities_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);



        //TODO: Aminity
        amenity_recycler =  view.findViewById(R.id.amenity_recycler);
        LinearLayoutManager layoutManagerTop4 = new LinearLayoutManager(getActivity())
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
        layoutManagerTop4.setOrientation(LinearLayoutManager.HORIZONTAL);
        amenity_recycler.setLayoutManager(layoutManagerTop4);
        amenity_recycler.setHasFixedSize(true);
        amenity_recycler.setItemViewCacheSize(10);
        amenity_recycler.setDrawingCacheEnabled(true);
        amenity_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


        //TODO: CategoryList
        amenity_recycler =  view.findViewById(R.id.amenity_recycler);
        LinearLayoutManager layoutManagerTop5 = new LinearLayoutManager(getActivity())
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
        layoutManagerTop5.setOrientation(LinearLayoutManager.VERTICAL);
        categoryhome_list_recycler.setLayoutManager(layoutManagerTop5);
        categoryhome_list_recycler.setHasFixedSize(true);
        categoryhome_list_recycler.setItemViewCacheSize(10);
        categoryhome_list_recycler.setDrawingCacheEnabled(true);
        categoryhome_list_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);





        if (ConnectivityReceiver.isConnected())
        {
            makeCitiyRequest(id);
        } else {
            Toast.makeText(getActivity(), "Network Issue! Please try again", Toast.LENGTH_SHORT).show();
        }

        listener();
        return  view;

    }

    void listener()
    {
        category_recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), category_recycler, new RecyclerTouchListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                cityDetail_category_adapter.updateData(position);

                if(position == 0)
                {
                    home_cover.setVisibility(View.VISIBLE);
                    category_type_cover.setVisibility(View.GONE);
                }
                else
                {
                    home_cover.setVisibility(View.GONE);
                    category_type_cover.setVisibility(View.VISIBLE);
                    for (int i=0;i<featureList.size();i++)
                    {
                        if(category_cityDetailModels.get(position).getId() == featureList.get(i).getCategory_id())
                        {
                                    header_name.setText(featureList.get(i).getCategory_name());
                                    Log.d("Feature val:",featureList.get(i).getCategory_name());
                                    Log.d("Size",String.valueOf(featureList.get(i).getPlacesList().size()));

                                    place_placeDetailList = new ArrayList<>();
                                    place_placeDetailList = featureList.get(i).getPlacesList();

                                    if(place_placeDetailList.size()>0)
                                    {
                                        no_places_layout_places.setVisibility(View.GONE);
                                        CityData_PlacesType_Adapter cityData_placesType_adapter = new CityData_PlacesType_Adapter(place_placeDetailList, 0);
                                        category_type_recycler.setAdapter(cityData_placesType_adapter);
                                        cityData_placesType_adapter.notifyDataSetChanged();
                                    }
                                    else
                                    {
                                        no_places_layout_places.setVisibility(View.VISIBLE);

                                    }
                        }
                    }
                }

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        othercities_recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), othercities_recycler, new RecyclerTouchListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                androidx.fragment.app.Fragment fma1 = new CityDetail();
                androidx.fragment.app.FragmentManager fragmentManager1 = getFragmentManager();
                Bundle args = new Bundle();
                args.putInt("id", otherCityList.get(position).getId());

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

        category_type_recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), category_type_recycler, new RecyclerTouchListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                //
                int id = place_placeDetailList.get(position).getId();

                androidx.fragment.app.Fragment fma1 = new MapsFragment();
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

    public void makeCitiyRequest(int id)
    {

        showPogressdialog();
        String tag_json_obj = "json_socity_req";


        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.GET,
                BaseURL.CITYBYID+id, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response)
            {


                try
                {

                    Gson gson = new Gson();


                    String checkstatus = response.getString("code");
                    String mesage = response.getString("message");





                    if (checkstatus.equalsIgnoreCase("200"))
                    {

                        JSONObject object=new JSONObject(String.valueOf(response.getJSONObject("data")));


                        Log.d("City Detail Url Data: ",String.valueOf(object));

                        data = gson.fromJson(String.valueOf(object), CityData.class);

                        citymodel =new City();
                        citymodel = data.getCityList();


                        category_cityDetailModels =new ArrayList<>();

                        Category_CityDetailModel modelcat= new Category_CityDetailModel(1,"Home",0,"home",0,"none",null);
                        category_cityDetailModels.add(modelcat);

                        for(int i=0;i<data.getCategoryList().size();i++)
                        {
                            category_cityDetailModels.add(data.getCategoryList().get(i));
                        }

                     //   category_cityDetailModels = data.getCategoryList();


                        placesByCategoryList = new ArrayList<>();
                        placesByCategoryList = data.getPlacesByCategoryList();

                        otherCityList = new ArrayList<>();
                        otherCityList = data.getOtherCityList();

                        place_typeList = new ArrayList<>();
                        place_typeList = data.getPlace_typeList();

                        amenityList = new ArrayList<>();
                        amenityList = data.getAmenityList();

                        featureList = new ArrayList<>();
                        featureList = data.getFeatureList();

                        setData();

                        if(hud.isShowing()){
                            hud.dismiss();
                        }

                    }
                    else
                    {

                    }
                }
                catch (Exception e)
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

    public void showPogressdialog(){
        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    void setData()
    {

        try {
            if (citymodel.getBanner() != "")
            {
                CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getActivity());
                circularProgressDrawable.setStrokeWidth(5f);
                circularProgressDrawable.setCenterRadius(30f);
                circularProgressDrawable.start();
                Glide.with(getActivity())
                        .load(BaseURL.IMG_BASE_URL + citymodel.getBanner())
                        .centerCrop()
                        .placeholder(circularProgressDrawable)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(imageview);
            }


            if (citymodel.getIntro() != null)
            {
                place_name.setText(citymodel.getIntro());
            }
            else
            {
                place_name.setVisibility(View.GONE);
            }


            title.setText(citymodel.getName());
            description.setText(citymodel.getDescription());
            if(citymodel.getCurrency()!=null && citymodel.getCurrency()!= "") {
                tv_currency.setText(citymodel.getCurrency());
            }
            else
            {
                cover_currency.setVisibility(View.GONE);
            }

            if(citymodel.getLanguage()!=null && citymodel.getLanguage()!= "") {
                tv_lagnuage.setText(citymodel.getLanguage());
            }
            else
            {
                cover_language.setVisibility(View.GONE);
            }

            if(citymodel.getBest_time_to_visit()!=null && citymodel.getBest_time_to_visit()!= "") {
                tv_best_time_visit.setText(citymodel.getBest_time_to_visit());
            }
            else
            {
                cover_visit_time.setVisibility(View.GONE);
            }



            tv_created_at.setText(citymodel.getCreated_at());
            tv_updated_at.setText(citymodel.getUpdated_at());

            latitude = citymodel.getLat();
            longitude = citymodel.getLng();


            cityDetail_category_adapter = new Category_Adapter(category_cityDetailModels, 0);
            category_recycler.setAdapter(cityDetail_category_adapter);
            cityDetail_category_adapter.notifyDataSetChanged();

            CityDetail_OtherCity_Adapter cityDetail_otherCity_adapter = new CityDetail_OtherCity_Adapter(otherCityList, 0);
            othercities_recycler.setAdapter(cityDetail_otherCity_adapter);
            cityDetail_otherCity_adapter.notifyDataSetChanged();

            CityData_Amenity_Adapter cityData_amenity_adapter = new CityData_Amenity_Adapter(amenityList, 0);
            amenity_recycler.setAdapter(cityData_amenity_adapter);
            cityData_amenity_adapter.notifyDataSetChanged();

            CityDetail_Home_CategoryList cityDetail_home_categoryList = new CityDetail_Home_CategoryList(CityDetail.this, category_cityDetailModels, featureList);
            categoryhome_list_recycler.setAdapter(cityDetail_home_categoryList);
            cityDetail_home_categoryList.notifyDataSetChanged();


//        CityData_PlacesType_Adapter cityData_placesType_adapter= new CityData_PlacesType_Adapter(place_typeList,0);
//        placesType_recycler.setAdapter(cityData_placesType_adapter);
//        cityData_placesType_adapter.notifyDataSetChanged();


  //          cover.setAlpha(1);
        }
        catch (Exception e)
        {
            Log.d(TAG,e.toString());
        }

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