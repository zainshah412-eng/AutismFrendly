package com.AutismFriendlyWorld.tcc.Adapter.PlacesFolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Place.OpeningHour_PlaceDetail;
import com.AutismFriendlyWorld.tcc.R;

import java.util.List;

public class Opening_Adapter
        extends RecyclerView.Adapter<Opening_Adapter.MyViewHolder>
{

    private List<OpeningHour_PlaceDetail> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {



        TextView tv_day;
        TextView tv_time;





        public MyViewHolder(View view) {
            super(view);


            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_day = (TextView) view.findViewById(R.id.tv_day);



        }
    }

    public Opening_Adapter(List<OpeningHour_PlaceDetail> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public Opening_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_opening_place_detail, parent, false);
        context = parent.getContext();
        return new Opening_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(Opening_Adapter.MyViewHolder holder, int position)
    {
        OpeningHour_PlaceDetail mList = modelList.get(position);


        try
        {

         holder.tv_day.setText(mList.getTitle());
         holder.tv_time.setText(mList.getValue());





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



