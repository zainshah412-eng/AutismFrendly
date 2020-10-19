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
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Model.Cities.My_Cities_Model;
import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class Popular_Cities_Full_Screen_Adapter extends RecyclerView.Adapter<Popular_Cities_Full_Screen_Adapter.MyViewHolder>
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
        public TextView tv_name, tv_description,tv_cityname;
        public String method;


        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            iv__img = (ImageView) view.findViewById(R.id.iv__img);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            tv_cityname = (TextView) view.findViewById(R.id.tv_cityname);
        }
    }

    public Popular_Cities_Full_Screen_Adapter(List<My_Cities_Model> modelList,int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public Popular_Cities_Full_Screen_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_full_screen_cites, parent, false);
        context = parent.getContext();
        return new Popular_Cities_Full_Screen_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(Popular_Cities_Full_Screen_Adapter.MyViewHolder holder, int position)
    {
        My_Cities_Model mList = modelList.get(position);


        try {

            if(mList.getCountry()!= null)
            {
                holder.tv_name.setText(mList.getCountry().getName());
            }
            else
            {
                holder.tv_name.setText("");
            }
            if(mList.getIntro()!=null)
            {
                holder.tv_description.setText(mList.getIntro());
            }
            else
            {
                holder.tv_description.setText("");
            }
            if(mList.getName()!=null)
            {
                holder.tv_cityname.setText(mList.getName());
            }
            else
            {
                holder.tv_cityname.setText("");
            }

            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            if(mList.getBanner()!="")
            {
                Glide.with(context)
                        .load(BaseURL.IMG_BASE_URL + mList.getBanner())
                        .placeholder(circularProgressDrawable)
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

