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
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Model.CityDetail.Amenities.Amenity;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class CityData_Amenity_Adapter extends RecyclerView.Adapter<CityData_Amenity_Adapter.MyViewHolder>
{

    private List<Amenity> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView iv__img;
        CardView cardView;
        public TextView tv_name;
        public String method;


        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            iv__img = (ImageView) view.findViewById(R.id.iv__img);
            tv_name = (TextView) view.findViewById(R.id.tv_name);

        }
    }

    public CityData_Amenity_Adapter(List<Amenity> modelList,int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public CityData_Amenity_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_amenity, parent, false);
        context = parent.getContext();
        return new CityData_Amenity_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(CityData_Amenity_Adapter.MyViewHolder holder, int position)
    {
        Amenity mList = modelList.get(position);


        try {

            holder.tv_name.setText(mList.getName());


            if(mList.getIcon()!="")
            {
//                Glide.with(context)
//                        .load(BaseURL.IMG_BASE_URL + mList.getIcon())
//                        .centerCrop()
//                        .placeholder(R.drawable.logo_white_text)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .dontAnimate()
//                        .into(holder.iv__img);
                CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
                circularProgressDrawable.setStrokeWidth(5f);
                circularProgressDrawable.setCenterRadius(30f);
                circularProgressDrawable.start();

                Utils.fetchSvg(context.getApplicationContext(), BaseURL.IMG_BASE_URL + mList.getIcon(), holder.iv__img,circularProgressDrawable);
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
