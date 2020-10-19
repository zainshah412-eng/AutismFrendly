package com.AutismFriendlyWorld.tcc.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.AutismFriendlyWorld.tcc.Model.CityDetail.Category.Category_CityDetailModel;
import com.AutismFriendlyWorld.tcc.R;

import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.MyViewHolder>
{

    private List<Category_CityDetailModel> modelList;
    private LayoutInflater inflater;
    private Fragment currentFragment;

    private Context context;
    Integer itempPosition = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        public TextView tv_name;
        public View tv_view;



        public MyViewHolder(View view)
        {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_view = (View) view.findViewById(R.id.tv_view);

        }
    }

    public Category_Adapter(List<Category_CityDetailModel> modelList, int type)
    {
        this.modelList = modelList;

    }

    @Override
    public Category_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_city_detail, parent, false);
        context = parent.getContext();
        return new Category_Adapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(Category_Adapter.MyViewHolder holder, int position)
    {
        Category_CityDetailModel mList = modelList.get(position);


        try
        {
            if(itempPosition == position)
            {
                holder.tv_name.setTextColor(context.getResources().getColor(R.color.color_3));
                holder.tv_view.setBackgroundColor(context.getResources().getColor(R.color.red));
            }

            else
            {
                holder.tv_name.setTextColor(context.getResources().getColor(R.color.contact_name));
                holder.tv_view.setBackgroundColor(context.getResources().getColor(R.color.white));
            }

                holder.tv_name.setText(mList.getName());
        }
        catch (Exception e)
        {
            Log.d("Error Category",e.toString());
        }



    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    public void updateData(int position) {
        itempPosition=position;
        notifyDataSetChanged();
    }
}
