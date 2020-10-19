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

import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Reviews_PlaceDetail;
import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class Review_PlaceDetail_Adapter extends RecyclerView.Adapter<Review_PlaceDetail_Adapter.MyViewHolder>
{

    private List<Reviews_PlaceDetail> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        CardView cardView;
        ImageView iv__img;
        public TextView tv_name;
        public RatingBar ratingBar;

        public TextView tv_time;
        public TextView tv_comment;




        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            iv__img = (ImageView) view.findViewById(R.id.iv__img);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_comment = (TextView) view.findViewById(R.id.tv_comment);


        }
    }

    public Review_PlaceDetail_Adapter(List<Reviews_PlaceDetail> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public Review_PlaceDetail_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reviews_place_detail, parent, false);
        context = parent.getContext();
        return new Review_PlaceDetail_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(Review_PlaceDetail_Adapter.MyViewHolder holder, int position)
    {
        Reviews_PlaceDetail mList = modelList.get(position);


        try
        {

            holder.tv_name.setText(mList.getUser().getName());
            holder.ratingBar.setRating(mList.getScore());
            holder.tv_time.setText(mList.getUser().getCreatedAt());
            holder.tv_comment.setText(mList.getComment());

            if(mList.getUser().getAvatar()!="")
            {
                Glide.with(context)
                        .load(BaseURL.IMG_BASE_URL + mList.getUser().getAvatar())
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


