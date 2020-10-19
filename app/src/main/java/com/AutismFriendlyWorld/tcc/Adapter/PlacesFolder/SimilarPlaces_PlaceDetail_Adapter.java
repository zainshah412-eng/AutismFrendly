package com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.AutismFriendlyWorld.tcc.Config.BaseURL;
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Similar_places_PlaceDetail;
import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class SimilarPlaces_PlaceDetail_Adapter extends RecyclerView.Adapter<SimilarPlaces_PlaceDetail_Adapter.MyViewHolder>
{

    private List<Similar_places_PlaceDetail> modelList;
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





        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            iv__img = (ImageView) view.findViewById(R.id.iv__img);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            tv_currency = (TextView) view.findViewById(R.id.tv_currency);
            tv_lagnuage = (TextView) view.findViewById(R.id.tv_lagnuage);


        }
    }

    public SimilarPlaces_PlaceDetail_Adapter(List<Similar_places_PlaceDetail> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public SimilarPlaces_PlaceDetail_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_similarplaces_place_detail, parent, false);
        context = parent.getContext();
        return new SimilarPlaces_PlaceDetail_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(SimilarPlaces_PlaceDetail_Adapter.MyViewHolder holder, int position)
    {
        Similar_places_PlaceDetail mList = modelList.get(position);


        try
        {

            holder.tv_name.setText(mList.getName());
            if(mList.getAddress()!=null) {
                holder.tv_description.setText(mList.getAddress());
            }
            else {
                holder.tv_description.setVisibility(View.GONE);
            }



            if(mList.getThumb()!="")
            {
                Glide.with(context)
                        .load(BaseURL.IMG_BASE_URL + mList.getThumb())
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


