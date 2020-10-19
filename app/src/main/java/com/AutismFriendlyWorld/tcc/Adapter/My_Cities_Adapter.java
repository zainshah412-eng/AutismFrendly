package com.AutismFriendlyWorld.tcc.Adapter;

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
import com.AutismFriendlyWorld.tcc.Model.My_order_model;
import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class My_Cities_Adapter extends RecyclerView.Adapter<My_Cities_Adapter.MyViewHolder>
{

    private List<My_Cities_Model> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView iv__img;
        CardView cardView;
        public TextView tv_name, tv_description;
        public String method;


        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            iv__img = (ImageView) view.findViewById(R.id.iv__img);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
        }
    }

    public My_Cities_Adapter(List<My_Cities_Model> modelList,int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public My_Cities_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cites, parent, false);
        context = parent.getContext();
        return new My_Cities_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(My_Cities_Adapter.MyViewHolder holder, int position)
    {
        My_Cities_Model mList = modelList.get(position);


        try {

            if(callType ==1 )
            {
                holder.cardView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            holder.tv_name.setText(mList.getName());
            holder.tv_description.setText(mList.getIntro());


            if(mList.getBanner()!="")
            {
                Glide.with(context)
                        .load(BaseURL.IMG_BASE_URL + mList.getBanner())
                        .fitCenter()
                        .placeholder(R.drawable.logo_white_text)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(holder.iv__img);
            }


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
