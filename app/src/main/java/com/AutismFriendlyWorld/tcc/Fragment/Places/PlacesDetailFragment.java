package com.AutismFriendlyWorld.tcc.Fragment.Places;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.Opening_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.PlaceDetail_PlaceType_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.PlacesData_Amenity_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.PlacesDetail_Category_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.Places_Gallery_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.Review_PlaceDetail_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.SimilarPlaces_PlaceDetail_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder.SocailLinks_PlaceDetail_Adapter;
import com.AutismFriendlyWorld.tcc.Adapter.SliderAdapterExample;
import com.AutismFriendlyWorld.tcc.AppController;
import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Helper.RecyclerTouchListener;
import com.AutismFriendlyWorld.tcc.MainActivity;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.AmenitiesPlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Categories_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.City_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place.Socail_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.PlaceData;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place_types_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Reviews_PlaceDetail;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Similar_places_PlaceDetail;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PlacesDetailFragment extends Fragment
{

    private static String TAG = PlacesDetailFragment.class.getSimpleName();


    SliderView sliderView;

    RelativeLayout cover;

    //TODO: Place Data
    TextView place_address;
    ImageView cover_image;
    TextView title;
    RecyclerView gallery_recycler;
    TextView tv_description_place;
    TextView start_navigation;

    //TODO: City Detail

    ImageView iv__img;
    TextView tv_name_city;
    TextView tv_description_city;
    TextView tv_currency_city;
    TextView tv_lagnuage_city;
    TextView tv_best_time_visit_city;


    RecyclerView amenity_recycler;
    RecyclerView category_recycler;
    RecyclerView placesType_recycler;
    RecyclerView reviews_recycler;
    RecyclerView similarplaces_recycler;
    RecyclerView socailLinks_recycler;
    RecyclerView openingtime_recycler;

    TextView Rating;
    TextView phoneno;
    TextView email;
    TextView Rating_upper;
    TextView address;
    TextView rating_and_reviews_heading;
    TextView rating_count;
    RatingBar ratingBar;
    RatingBar ratingBar_upper;
    LinearLayout phone_cover;
    LinearLayout email_cover;
    LinearLayout map_cover;

    LinearLayout rating_review_cover;
    LinearLayout similarplaces_cover;

    PlaceData data;

    Place_PlaceDetail place_placeDetail;
    City_PlaceDetail city_placeDetail;
    List<AmenitiesPlaceDetail> amenityList;
    List<Categories_PlaceDetail> categoriesPlaceDetailList;
    List<Place_types_PlaceDetail> place_typeList;
    List<Similar_places_PlaceDetail> similarPlacesPlaceDetailList;
    List<Reviews_PlaceDetail> reviews_placeDetailList;
    List<Socail_PlaceDetail> socaillinks_placeDetailList;

    private Session_management sessionManagement;
    String get_id;
    int id;
    KProgressHUD hud;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_places_detail, container, false);
        sessionManagement = new Session_management(getActivity());
        get_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);

        id = getArguments().getInt("id");

  //      cover = view.findViewById(R.id.cover);

        //TODO: Image Slider

        sliderView = view.findViewById(R.id.imageSlider);



        //TODO: Place Data
         place_address = view.findViewById(R.id.place_address);;
         cover_image = view.findViewById(R.id.cover_image);;
         title = view.findViewById(R.id.title);;
         gallery_recycler = view.findViewById(R.id.gallery_recycler);;
         tv_description_place = view.findViewById(R.id.tv_description_place);;
         start_navigation = view.findViewById(R.id.start_navigation);;

        //TODO: City Detail

         iv__img  = view.findViewById(R.id.iv__img);;
         tv_name_city = view.findViewById(R.id.tv_name_city);;
         tv_description_city = view.findViewById(R.id.tv_description_city);;
         tv_currency_city = view.findViewById(R.id.tv_currency_city);;
         tv_lagnuage_city = view.findViewById(R.id.tv_lagnuage_city);;
         tv_best_time_visit_city = view.findViewById(R.id.tv_best_time_visit_city);;


         amenity_recycler = view.findViewById(R.id.amenity_recycler);;
         category_recycler = view.findViewById(R.id.category_recycler);;
         placesType_recycler = view.findViewById(R.id.placesType_recycler);;
         reviews_recycler = view.findViewById(R.id.reviews_recycler);;
         similarplaces_recycler = view.findViewById(R.id.similarplaces_recycler);;
         socailLinks_recycler = view.findViewById(R.id.socailLinks_recycler);;
         openingtime_recycler = view.findViewById(R.id.openingtime_recycler);;



         Rating = view.findViewById(R.id.Rating);
         Rating_upper = view.findViewById(R.id.Rating_upper);
         ratingBar_upper = view.findViewById(R.id.ratingBar_upper);
         ratingBar = view.findViewById(R.id.ratingBar);
         rating_and_reviews_heading = view.findViewById(R.id.rating_and_reviews_heading);
         rating_count = view.findViewById(R.id.rating_count);



         phoneno = view.findViewById(R.id.phoneno);
         phone_cover = view.findViewById(R.id.phone_cover);
         email = view.findViewById(R.id.email);
         email_cover = view.findViewById(R.id.email_cover);
         map_cover = view.findViewById(R.id.map_cover);
         address = view.findViewById(R.id.address);



        rating_review_cover = view.findViewById(R.id.rating_review_cover);
        similarplaces_cover = view.findViewById(R.id.similarplaces_cover);





         //TODO: AMENITY RECYCLER
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
        amenity_recycler.setLayoutManager(layoutManagerTop1);
        amenity_recycler.setHasFixedSize(true);
        amenity_recycler.setItemViewCacheSize(10);
        amenity_recycler.setDrawingCacheEnabled(true);
        amenity_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        //TODO: CATEGORY RECYCLER
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
        category_recycler.setLayoutManager(layoutManagerTop2);
        category_recycler.setHasFixedSize(true);
        category_recycler.setItemViewCacheSize(10);
        category_recycler.setDrawingCacheEnabled(true);
        category_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        //TODO: PLACES TYPE RECYCLER
        LinearLayoutManager layoutManagerTop3 = new LinearLayoutManager(getActivity())
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
        layoutManagerTop3.setOrientation(LinearLayoutManager.HORIZONTAL);
        placesType_recycler.setLayoutManager(layoutManagerTop3);
        placesType_recycler.setHasFixedSize(true);
        placesType_recycler.setItemViewCacheSize(10);
        placesType_recycler.setDrawingCacheEnabled(true);
        placesType_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        //TODO: REVIEWS RECYCLER
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
        reviews_recycler.setLayoutManager(layoutManagerTop4);
        reviews_recycler.setHasFixedSize(true);
        reviews_recycler.setItemViewCacheSize(10);
        reviews_recycler.setDrawingCacheEnabled(true);
        reviews_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


        //TODO: SIMILAR PLACES RECYCLER
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
        layoutManagerTop5.setOrientation(LinearLayoutManager.HORIZONTAL);
        similarplaces_recycler.setLayoutManager(layoutManagerTop5);
        similarplaces_recycler.setHasFixedSize(true);
        similarplaces_recycler.setItemViewCacheSize(10);
        similarplaces_recycler.setDrawingCacheEnabled(true);
        similarplaces_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        //TODO: Gallery RECYCLER
        LinearLayoutManager layoutManagerTop6 = new LinearLayoutManager(getActivity())
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
        layoutManagerTop6.setOrientation(LinearLayoutManager.HORIZONTAL);
        gallery_recycler.setLayoutManager(layoutManagerTop6);
        gallery_recycler.setHasFixedSize(true);
        gallery_recycler.setItemViewCacheSize(10);
        gallery_recycler.setDrawingCacheEnabled(true);
        gallery_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


        //TODO: Gallery RECYCLER
        LinearLayoutManager layoutManagerTop7 = new LinearLayoutManager(getActivity())
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
        layoutManagerTop7.setOrientation(LinearLayoutManager.HORIZONTAL);
        socailLinks_recycler.setLayoutManager(layoutManagerTop7);
        socailLinks_recycler.setHasFixedSize(true);
        socailLinks_recycler.setItemViewCacheSize(10);
        socailLinks_recycler.setDrawingCacheEnabled(true);
        socailLinks_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

     //TODO: Opening RECYCLER
        LinearLayoutManager layoutManagerTop8 = new LinearLayoutManager(getActivity())
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
        layoutManagerTop8.setOrientation(LinearLayoutManager.VERTICAL);
        openingtime_recycler.setLayoutManager(layoutManagerTop8);
        openingtime_recycler.setHasFixedSize(true);
        openingtime_recycler.setItemViewCacheSize(10);
        openingtime_recycler.setDrawingCacheEnabled(true);
        openingtime_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);




        if (ConnectivityReceiver.isConnected())

        {

            makePlacesRequest(id);


        } else

        {
            ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
        }

        listener();

        return view;
    }

    void listener()
    {
        similarplaces_recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), similarplaces_recycler, new RecyclerTouchListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                //
                int id = similarPlacesPlaceDetailList.get(position).getId();

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

    public void makePlacesRequest(int id)
    {

        showPogressdialog();
        String tag_json_obj = "json_socity_req";


        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.GET,
                BaseURL.PLACESBYID+id, null, new Response.Listener<JSONObject>() {

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

                        data = gson.fromJson(String.valueOf(object), PlaceData.class);


                        place_placeDetail = new  Place_PlaceDetail();
                        place_placeDetail = data.getPlace_placeDetail();


                        socaillinks_placeDetailList = new ArrayList<>();
                        socaillinks_placeDetailList = place_placeDetail.getSocial();

                        city_placeDetail = new City_PlaceDetail();
                        city_placeDetail = data.getCity_placeDetail();

                        amenityList =new ArrayList<>();
                        amenityList = (List<AmenitiesPlaceDetail>) data.getAmenitiesPlaceDetail();

                        categoriesPlaceDetailList = new ArrayList<>();
                        categoriesPlaceDetailList = (List<Categories_PlaceDetail>) data.getCategories_placeDetail();

                        place_typeList = new ArrayList<>();
                        place_typeList = data.getPlace_types_placeDetail();

                        reviews_placeDetailList = new ArrayList<>();
                        reviews_placeDetailList = data.getReviews_placeDetail();

                        similarPlacesPlaceDetailList = new ArrayList<>();
                        similarPlacesPlaceDetailList = data.getSimilar_places_placeDetail();




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
            if (place_placeDetail.getThumb() != "")
            {
                CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getActivity());
                circularProgressDrawable.setStrokeWidth(5f);
                circularProgressDrawable.setCenterRadius(30f);
                circularProgressDrawable.start();

                Glide.with(getActivity())
                        .load(BaseURL.IMG_BASE_URL + place_placeDetail.getThumb())
                        .centerCrop()
                        .placeholder(circularProgressDrawable)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(cover_image);
            }


            //TODO: Place Data
            place_address.setText(place_placeDetail.getAddress());
            title.setText(place_placeDetail.getName());
            tv_description_place.setText(place_placeDetail.getDescription());

            Places_Gallery_Adapter places_gallery_adapter = new Places_Gallery_Adapter(place_placeDetail.getGallery(), 0);
            gallery_recycler.setAdapter(places_gallery_adapter);
            places_gallery_adapter.notifyDataSetChanged();


            SliderAdapterExample adapter = new SliderAdapterExample(getActivity());

            sliderView.setSliderAdapter(adapter);

            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            sliderView.setIndicatorSelectedColor(Color.WHITE);
            sliderView.setIndicatorUnselectedColor(Color.GRAY);
            sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
            sliderView.startAutoCycle();
            adapter.renewItems(place_placeDetail.getGallery());


            //TODO: City Detail

            if (place_placeDetail.getThumb() != "")
            {
                CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getActivity());
                circularProgressDrawable.setStrokeWidth(5f);
                circularProgressDrawable.setCenterRadius(30f);
                circularProgressDrawable.start();

                Glide.with(getActivity())
                        .load(BaseURL.IMG_BASE_URL + city_placeDetail.getThumb())
                        .centerCrop()
                        .placeholder(circularProgressDrawable)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(iv__img);
            }


            tv_name_city.setText(city_placeDetail.getName());
            tv_description_city.setText(city_placeDetail.getDescription());
            tv_currency_city.setText(city_placeDetail.getCurrency());
            tv_lagnuage_city.setText(city_placeDetail.getLanguage());
            tv_best_time_visit_city.setText(city_placeDetail.getBestTimeToVisit());




            if(reviews_placeDetailList.size()>1) {
                Rating_upper.setText(String.valueOf(data.getReview_score_avg()) + getActivity().getResources().getString(R.string.star)
                        + "(" + String.valueOf(reviews_placeDetailList.size() + " reviews) -" + city_placeDetail.getCurrency() + ""));
            }
            else
            {
                Rating_upper.setText(String.valueOf(data.getReview_score_avg()) + getActivity().getResources().getString(R.string.star)
                        + "(" + String.valueOf(reviews_placeDetailList.size() + " review) - " + city_placeDetail.getCurrency() + ""));

            }
            rating_and_reviews_heading.setText("Review ("+String.valueOf(reviews_placeDetailList.size())+")");
            rating_count.setText(String.valueOf(data.getReview_score_avg())+ " " + getActivity().getResources().getString(R.string.star));

            if (place_placeDetail.getEmail() != null) {
                email.setText(place_placeDetail.getEmail());
                email_cover.setVisibility(View.VISIBLE);
            } else {
                email_cover.setVisibility(View.GONE);
            }

            if (place_placeDetail.getPhoneNumber() != null)
            {
                phone_cover.setVisibility(View.VISIBLE);
                phoneno.setText(place_placeDetail.getPhoneNumber());
            }
            else
                {
                    phone_cover.setVisibility(View.GONE);
            }
            if (place_placeDetail.getAddress() != null)
            {
                map_cover.setVisibility(View.VISIBLE);
                address.setText(place_placeDetail.getAddress());
            }
            else
                {
                    map_cover.setVisibility(View.GONE);
            }

            ratingBar_upper.setRating(data.getReview_score_avg());


            PlacesData_Amenity_Adapter cityData_amenity_adapter = new PlacesData_Amenity_Adapter(getActivity(),amenityList, 0);
            amenity_recycler.setAdapter(cityData_amenity_adapter);
            cityData_amenity_adapter.notifyDataSetChanged();


            PlacesDetail_Category_Adapter cityDetail_category_adapter = new PlacesDetail_Category_Adapter(categoriesPlaceDetailList, 0);
            category_recycler.setAdapter(cityDetail_category_adapter);
            cityDetail_category_adapter.notifyDataSetChanged();

            PlaceDetail_PlaceType_Adapter placeDetail_placeType_adapter = new PlaceDetail_PlaceType_Adapter(place_typeList, 0);
            placesType_recycler.setAdapter(placeDetail_placeType_adapter);
            placeDetail_placeType_adapter.notifyDataSetChanged();


            Opening_Adapter opening_adapter = new Opening_Adapter(place_placeDetail.getOpeningHour(), 0);
            openingtime_recycler.setAdapter(opening_adapter);
            opening_adapter.notifyDataSetChanged();

            if(reviews_placeDetailList.size()>0)
            {

                Review_PlaceDetail_Adapter review_placeDetail_adapter = new Review_PlaceDetail_Adapter(reviews_placeDetailList, 0);
                reviews_recycler.setAdapter(review_placeDetail_adapter);
                review_placeDetail_adapter.notifyDataSetChanged();

            }
            else
            {
                rating_review_cover.setVisibility(View.GONE);
            }
            if(similarPlacesPlaceDetailList.size()>0) {
                SimilarPlaces_PlaceDetail_Adapter similarPlaces_placeDetail_adapter = new SimilarPlaces_PlaceDetail_Adapter(similarPlacesPlaceDetailList, 0);
                similarplaces_recycler.setAdapter(similarPlaces_placeDetail_adapter);
                similarPlaces_placeDetail_adapter.notifyDataSetChanged();
            }
            else
            {
                similarplaces_cover.setVisibility(View.GONE);
            }

            SocailLinks_PlaceDetail_Adapter socailLinks_placeDetail_adapter = new SocailLinks_PlaceDetail_Adapter(socaillinks_placeDetailList, 0);
            socailLinks_recycler.setAdapter(socailLinks_placeDetail_adapter);
            socailLinks_placeDetail_adapter.notifyDataSetChanged();

 //           cover.setAlpha(1);

        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();
        }

    }

}