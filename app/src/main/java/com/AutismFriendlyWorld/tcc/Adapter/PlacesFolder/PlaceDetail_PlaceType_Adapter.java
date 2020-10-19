package com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place_types_PlaceDetail;
import com.AutismFriendlyWorld.tcc.R;

import java.util.List;

public class PlaceDetail_PlaceType_Adapter extends RecyclerView.Adapter<PlaceDetail_PlaceType_Adapter.MyViewHolder>
{

    private List<Place_types_PlaceDetail> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        CardView cardView;
        public TextView tv_name;
        public String method;


        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);

        }
    }

    public PlaceDetail_PlaceType_Adapter(List<Place_types_PlaceDetail> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public PlaceDetail_PlaceType_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places_city_detail, parent, false);
        context = parent.getContext();
        return new PlaceDetail_PlaceType_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(PlaceDetail_PlaceType_Adapter.MyViewHolder holder, int position)
    {
        Place_types_PlaceDetail mList = modelList.get(position);


        try
        {

            holder.tv_name.setText(mList.getName());

        }
        catch (Exception e)
        {

        }



    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}
