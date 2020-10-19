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
import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place.Socail_PlaceDetail;

import com.AutismFriendlyWorld.tcc.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.List;

public class SocailLinks_PlaceDetail_Adapter
        extends RecyclerView.Adapter<SocailLinks_PlaceDetail_Adapter.MyViewHolder>
{

    private List<Socail_PlaceDetail> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {


        ImageView iv__img;
        TextView tv_name;





        public MyViewHolder(View view) {
            super(view);

            iv__img = (ImageView) view.findViewById(R.id.iv__img);
            tv_name = (TextView) view.findViewById(R.id.tv_name);



        }
    }

    public SocailLinks_PlaceDetail_Adapter(List<Socail_PlaceDetail> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public SocailLinks_PlaceDetail_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_socaillinks_place_detail, parent, false);
        context = parent.getContext();
        return new SocailLinks_PlaceDetail_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(SocailLinks_PlaceDetail_Adapter.MyViewHolder holder, int position)
    {
        Socail_PlaceDetail mList = modelList.get(position);


        try
        {

            if(mList.getName().equalsIgnoreCase("Facebook"))
            {
                holder.iv__img.setImageDrawable(context.getResources().getDrawable(R.drawable.facebook_32));
                holder.tv_name.setText(mList.getName());
            }
            if(mList.getName().equalsIgnoreCase("Gmail"))
            {
                holder.iv__img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_baseline_email_24));
                holder.tv_name.setText(mList.getName());
            }
            if(mList.getName().equalsIgnoreCase("Instagram"))
            {
                holder.iv__img.setImageDrawable(context.getResources().getDrawable(R.drawable.instagram));
                holder.tv_name.setText(mList.getName());
            }
            if(mList.getName().equalsIgnoreCase("Linkedin"))
            {
                holder.iv__img.setImageDrawable(context.getResources().getDrawable(R.drawable.linkedin));
                holder.tv_name.setText(mList.getName());
            }
            if(mList.getName().equalsIgnoreCase("Twitter"))
            {
                holder.iv__img.setImageDrawable(context.getResources().getDrawable(R.drawable.twitter));
                holder.tv_name.setText(mList.getName());
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


