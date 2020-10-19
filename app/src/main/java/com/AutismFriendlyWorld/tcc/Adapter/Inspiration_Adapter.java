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
import com.AutismFriendlyWorld.tcc.Model.Inspiration.Inspiration;
import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class Inspiration_Adapter extends RecyclerView.Adapter<Inspiration_Adapter.MyViewHolder>
{

    private List<Inspiration> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView iv__img;
        CardView cardView;
        public TextView tv_name;



        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            iv__img = (ImageView) view.findViewById(R.id.iv__img);
            tv_name = (TextView) view.findViewById(R.id.tv_name);

        }
    }

    public Inspiration_Adapter(List<Inspiration> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public Inspiration_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_inspration, parent, false);
        context = parent.getContext();
        return new Inspiration_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(Inspiration_Adapter.MyViewHolder holder, int position)
    {
        Inspiration mList = modelList.get(position);


        try {

            holder.tv_name.setText(mList.getTitle());


            if(mList.getThumb()!="")
            {
                CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
                circularProgressDrawable.setStrokeWidth(5f);
                circularProgressDrawable.setCenterRadius(30f);
                circularProgressDrawable.start();

                Glide.with(context)
                        .load(BaseURL.IMG_BASE_URL + mList.getThumb())
                        .centerCrop()
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

