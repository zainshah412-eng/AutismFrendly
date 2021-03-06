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

import com.AutismFriendlyWorld.tcc.Model.Places.PlacesDetail.Categories_PlaceDetail;
import com.AutismFriendlyWorld.tcc.R;

import java.util.List;

public class PlacesDetail_Category_Adapter extends RecyclerView.Adapter<PlacesDetail_Category_Adapter.MyViewHolder>
{

    private List<Categories_PlaceDetail> modelList;
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


        public MyViewHolder(View view)
        {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            imageView = (ImageView) view.findViewById(R.id.iv__img);

        }
    }

    public PlacesDetail_Category_Adapter(List<Categories_PlaceDetail> modelList, int type)
    {
        this.modelList = modelList;
        this.callType=type;
    }

    @Override
    public PlacesDetail_Category_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_city_detail, parent, false);
        context = parent.getContext();
        return new PlacesDetail_Category_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(PlacesDetail_Category_Adapter.MyViewHolder holder, int position)
    {
        Categories_PlaceDetail mList = modelList.get(position);


        try
        {

            holder.tv_name.setText(mList.getName());
            if(mList.getName().equalsIgnoreCase("stay")) {
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_stay));
            }
            else if (mList.getName().equalsIgnoreCase("Eat & Drink")) {
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_eat_and_drink));
            }
            else if (mList.getName().equalsIgnoreCase("See & Do")) {
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_see_and_do));
            }
            else if (mList.getName().equalsIgnoreCase("Visit")) {
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
