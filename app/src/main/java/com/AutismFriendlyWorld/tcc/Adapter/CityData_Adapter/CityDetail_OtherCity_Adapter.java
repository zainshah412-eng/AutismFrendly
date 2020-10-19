package com.AutismFriendlyWorld.tcc.Adapter.CityData_Adapter;

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
import com.AutismFriendlyWorld.tcc.Model.CityDetail.OtherCities.OtherCity;
import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class CityDetail_OtherCity_Adapter extends RecyclerView.Adapter<CityDetail_OtherCity_Adapter.MyViewHolder>
{

    private List<OtherCity> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        CardView cardView;
        ImageView iv__img;
        public TextView tv_name;
        public TextView tv_description;
        public TextView tv_currency;
        public TextView tv_lagnuage;
        public String method;


        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            tv_currency = (TextView) view.findViewById(R.id.tv_currency);
            tv_lagnuage = (TextView) view.findViewById(R.id.tv_lagnuage);
            iv__img = (ImageView) view.findViewById(R.id.iv__img);
        }
    }

    public CityDetail_OtherCity_Adapter(List<OtherCity> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public CityDetail_OtherCity_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_othercity_city_detail, parent, false);
        context = parent.getContext();
        return new CityDetail_OtherCity_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(CityDetail_OtherCity_Adapter.MyViewHolder holder, int position)
    {
        OtherCity mList = modelList.get(position);


        try
        {

            holder.tv_name.setText(mList.getName());
            holder.tv_description.setText(mList.getIntro());
            holder.tv_currency.setText(mList.getCurrency());
            holder.tv_lagnuage.setText(mList.getLanguage());

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

