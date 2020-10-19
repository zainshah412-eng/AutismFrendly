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

import com.AutismFriendlyWorld.tcc.Model.CityDetail.Category.Category_CityDetailModel;
import com.AutismFriendlyWorld.tcc.R;

import java.util.List;

public class CityDetail_Category_Adapter extends RecyclerView.Adapter<CityDetail_Category_Adapter.MyViewHolder>
{

    private List<Category_CityDetailModel> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        public TextView tv_name;



        public MyViewHolder(View view)
        {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);

        }
    }

    public CityDetail_Category_Adapter(List<Category_CityDetailModel> modelList, int type)
    {
        this.modelList = modelList;

    }

    @Override
    public CityDetail_Category_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_city_detail, parent, false);
        context = parent.getContext();
        return new CityDetail_Category_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(CityDetail_Category_Adapter.MyViewHolder holder, int position)
    {
        Category_CityDetailModel mList = modelList.get(position);


        try
        {
            holder.tv_name.setText(mList.getName());
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
