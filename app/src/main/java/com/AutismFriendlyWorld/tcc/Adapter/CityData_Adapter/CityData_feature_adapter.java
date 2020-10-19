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

import com.AutismFriendlyWorld.tcc.Model.CityDetail.Features.Feature;
import com.AutismFriendlyWorld.tcc.R;

import java.util.List;

public class CityData_feature_adapter extends RecyclerView.Adapter<CityData_feature_adapter.MyViewHolder>
{

    private List<Feature> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    int callType=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        CardView cardView;
        public TextView tv_name;
        public String method;
        public ImageView imageView;


        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            imageView = (ImageView) view.findViewById(R.id.iv__img);

        }
    }

    public CityData_feature_adapter(List<Feature> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public CityData_feature_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feature_city_detail, parent, false);
        context = parent.getContext();
        return new CityData_feature_adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(CityData_feature_adapter.MyViewHolder holder, int position)
    {
        Feature mList = modelList.get(position);


        try
        {

            holder.tv_name.setText(mList.getCategory_name());

            if(mList.getCategory_name().equalsIgnoreCase("stay")) {
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_stay));
            }
            else if (mList.getCategory_name().equalsIgnoreCase("Eat & Drink")) {
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_eat_and_drink));
            }
            else if (mList.getCategory_name().equalsIgnoreCase("See & Do")) {
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_see_and_do));
            }
            else if (mList.getCategory_name().equalsIgnoreCase("Visit")) {
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_visit));
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
