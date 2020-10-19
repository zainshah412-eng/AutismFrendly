package com.AutismFriendlyWorld.tcc.Adapter.CityData_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;


import com.AutismFriendlyWorld.tcc.Config.BaseURL;

import com.AutismFriendlyWorld.tcc.Fragment.Places.MapsFragment;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place_PlaceDetail;
import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class CityData_PlacesType_Adapter extends RecyclerView.Adapter<CityData_PlacesType_Adapter.MyViewHolder>
{

    private List<Place_PlaceDetail> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView iv__img;
        CardView cardView;
        public TextView tv_placename, tv_address,tv_rating;
        public String method;


        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            iv__img = (ImageView) view.findViewById(R.id.iv__img);
            tv_placename = (TextView) view.findViewById(R.id.tv_placename);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_rating = (TextView) view.findViewById(R.id.tv_rating);
        }
    }

    public CityData_PlacesType_Adapter(List<Place_PlaceDetail> modelList,int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }
    public CityData_PlacesType_Adapter(Fragment fragment,List<Place_PlaceDetail> modelList,int type)
    {
        this.modelList = modelList;
        this.callType=type;
        this.currentFragment=fragment;
    }

    @Override
    public CityData_PlacesType_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places_type_screen_cites, parent, false);
        context = parent.getContext();
        return new CityData_PlacesType_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(CityData_PlacesType_Adapter.MyViewHolder holder, int position)
    {
        final Place_PlaceDetail mList = modelList.get(position);


        try {

            if(mList.getName()!= null)
            {
                holder.tv_placename.setText(mList.getName());
            }
            else
            {
                holder.tv_placename.setText("");
            }
            if(mList.getAddress()!=null)
            {
                holder.tv_address.setText(mList.getAddress());
            }
            else
            {
                holder.tv_address.setText("");
            }
            if(mList.getAvg_review().get(0).getAggregate()!=null)
            {
                holder.tv_rating.setText(mList.getAvg_review().get(0).getAggregate()+"("+String.valueOf(mList.getReviews_count())+")");
            }
            else
            {
                holder.tv_rating.setText("");
            }

            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            if(mList.getThumb()!="")
            {
                Glide.with(context)
                        .load(BaseURL.IMG_BASE_URL + mList.getThumb())
                        .placeholder(circularProgressDrawable)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(holder.iv__img);
            }

            if(callType ==  1)
            {
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  int id = place_placeDetailList.get(position).getId();

                        androidx.fragment.app.Fragment fma1 = new MapsFragment();
                        androidx.fragment.app.FragmentManager fragmentManager1 = currentFragment.getFragmentManager();
                        Bundle args = new Bundle();
                        args.putInt("id", mList.getId());

                        fma1.setArguments(args);
                        String backStateName2 = fma1.getClass().getName();

                        FragmentManager manager2 = currentFragment.getActivity().getSupportFragmentManager();
                        boolean fragmentPopped2 = manager2.popBackStackImmediate(backStateName2, 0);

                        if (!fragmentPopped2) { //fragment not in back stack, create it.
                            FragmentTransaction ft = manager2.beginTransaction();
                            ft.add(R.id.contentPanel, fma1);
                            ft.addToBackStack(backStateName2);
                            ft.commit();
                        }
                    }
                });
            }

        }
        catch (Exception e)
        {

        }



    }

    @Override
    public int getItemCount()
    {
        return modelList.size();
    }

}

