package com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Model.Cities.My_Cities_Model;
import com.AutismFriendlyWorld.tcc.Model.Places.PlaceModel;
import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class Places_Adapter extends RecyclerView.Adapter<Places_Adapter.MyViewHolder>
{

    private List<PlaceModel> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        CardView cardView;
        public TextView tv_name,tv_address ,tv_description;
        public String method;


        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
        }
    }

    public Places_Adapter(List<PlaceModel> modelList,int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public Places_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places, parent, false);
        context = parent.getContext();
        return new Places_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(Places_Adapter.MyViewHolder holder, int position)
    {
        PlaceModel mList = modelList.get(position);


        try {

            if(callType ==1 )
            {
                holder.cardView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            holder.tv_name.setText(mList.getName());
            holder.tv_address.setText(mList.getAddress());
            holder.tv_description.setText(mList.getDescription());



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
