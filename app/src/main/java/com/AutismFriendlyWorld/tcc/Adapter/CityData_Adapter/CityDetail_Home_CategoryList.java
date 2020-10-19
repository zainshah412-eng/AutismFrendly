package com.AutismFriendlyWorld.tcc.Adapter.CityData_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.AutismFriendlyWorld.tcc.Fragment.City.CityDetail;

import com.AutismFriendlyWorld.tcc.Helper.RecyclerTouchListener;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.Category.Category_CityDetailModel;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.Features.Feature;
import com.AutismFriendlyWorld.tcc.R;

import java.util.List;

public class CityDetail_Home_CategoryList extends RecyclerView.Adapter<CityDetail_Home_CategoryList.MyViewHolder>
{

    private List<Category_CityDetailModel> category_cityDetailModelList;
    private List<Feature> featureList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        public TextView tv_name;
        public RecyclerView categorylist_recycler;
        public RelativeLayout no_places_layout_places;



        public MyViewHolder(View view)
        {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            categorylist_recycler = (RecyclerView) view.findViewById(R.id.categorylist_recycler);
            no_places_layout_places = (RelativeLayout) view.findViewById(R.id.no_places_layout_places);

        }
    }

    public CityDetail_Home_CategoryList(Fragment fragment,List<Category_CityDetailModel> modelList,List<Feature> modelList1)
    {
        this.category_cityDetailModelList = modelList;
        this.featureList = modelList1;
        this.currentFragment = fragment;

    }

    @Override
    public CityDetail_Home_CategoryList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_home_list_city_detail, parent, false);
        context = parent.getContext();
        return new CityDetail_Home_CategoryList.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(CityDetail_Home_CategoryList.MyViewHolder holder, int position)
    {
        Category_CityDetailModel mList = category_cityDetailModelList.get(position);


        try
        {
            if(position == 0)
            {
                holder.tv_name.setVisibility(View.GONE);
            }
            holder.tv_name.setText(mList.getName());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            holder.categorylist_recycler.setLayoutManager(gridLayoutManager);
            //  citieslist.addItemDecoration(new GridSpacingItemDecoration(10, dpToPx(5), true));
            holder.categorylist_recycler.setItemAnimator(new DefaultItemAnimator());
            holder.categorylist_recycler.setNestedScrollingEnabled(false);
            holder.categorylist_recycler.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));



            for (int i=0;i<featureList.size();i++)
            {
                if(mList.getId() == featureList.get(i).getCategory_id())
                {
                    if(featureList.get(i).getPlacesList().size()>0)
                    {
                        holder.no_places_layout_places.setVisibility(View.GONE);
                        CityDat_PlaceType_home_Adapter cityData_placesType_adapter = new CityDat_PlaceType_home_Adapter(currentFragment,featureList.get(i).getPlacesList(), 1);
                        holder.categorylist_recycler.setAdapter(cityData_placesType_adapter);
                        cityData_placesType_adapter.notifyDataSetChanged();
                    }
                    else {
                        holder.no_places_layout_places.setVisibility(View.VISIBLE);
                    }
                }

            }




        }
        catch (Exception e)
        {

        }



    }

    @Override
    public int getItemCount()
    {
        return category_cityDetailModelList.size();
    }
    private int dpToPx(int dp)
    {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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

}

