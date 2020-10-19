package com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.AmenitiesPlaceDetail;
import com.AutismFriendlyWorld.tcc.R;
import com.AutismFriendlyWorld.tcc.util.Utils;
import com.ahmadrosid.svgloader.SvgLoader;


import java.io.InputStream;
import java.util.List;

public class PlacesData_Amenity_Adapter extends RecyclerView.Adapter<PlacesData_Amenity_Adapter.MyViewHolder>
{

    private List<AmenitiesPlaceDetail> modelList;
    private LayoutInflater inflater;
    FragmentActivity fragmentActivity;

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

    public PlacesData_Amenity_Adapter(FragmentActivity fragmentActivity,List<AmenitiesPlaceDetail> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public PlacesData_Amenity_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places_amenity, parent, false);
        context = parent.getContext();
        return new PlacesData_Amenity_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(PlacesData_Amenity_Adapter.MyViewHolder holder, int position)
    {
        AmenitiesPlaceDetail mList = modelList.get(position);


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

//                CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
//                circularProgressDrawable.setStrokeWidth(5f);
//                circularProgressDrawable.setCenterRadius(30f);
//                circularProgressDrawable.start();
//                SvgLoader.pluck()
//                        .with(fragmentActivity)
//                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
//                        .load(BaseURL.IMG_BASE_URL + mList.getIcon(), holder.iv__img);



                //      GlideToVectorYou.init().with(context).load(Uri.parse(BaseURL.IMG_BASE_URL),holder.iv__img);
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
