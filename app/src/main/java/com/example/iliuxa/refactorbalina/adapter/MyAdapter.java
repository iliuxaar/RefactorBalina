package com.example.iliuxa.refactorbalina.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.pojo.Category;

import java.util.ArrayList;

/**
 * Created by Iliuxa on 31.10.16.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_CATEGORY = 1;
    private final static int TYPE_DISHES = 2;
    private int mResource;
    private ArrayList mData;

    public MyAdapter(int resource, ArrayList data){
        this.mResource = resource;
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_CATEGORY:return new CategoryResources(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.simple_category_list_item,parent,false));
            case TYPE_DISHES:return new DishesResources(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.simple_dishes_list_item,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case TYPE_CATEGORY:((CategoryResources)holder).bind(mData.get(position));break;
            case TYPE_DISHES:((DishesResources)holder).bind(mData.get(position));break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mResource == R.layout.simple_category_list_item){
            return TYPE_CATEGORY;
        } else if(mResource == R.layout.simple_dishes_list_item){
            return TYPE_DISHES;
        } else return 0;
    }


    private class CategoryResources extends MyViewHolder{
        private TextView mCategoryName;
        private ImageView mCategoryImage;

        public CategoryResources(View itemView) {
            super(itemView);
            mCategoryName = (TextView)itemView.findViewById(R.id.categoryNameText);
            mCategoryImage = (ImageView)itemView.findViewById(R.id.imageCategory);
        }
        @Override
        public void bind(Object data) {
            mCategoryName.setText(((Category)data).getName());
        }
    }

    private class DishesResources extends MyViewHolder {
        private TextView mDishName;
        private ImageView mDishImage;
        private TextView mDishPrice;


        public DishesResources(View itemView) {
            super(itemView);
            mDishName = (TextView)itemView.findViewById(R.id.dishNameText);
            mDishPrice = (TextView)itemView.findViewById(R.id.priceText);
            mDishImage = (ImageView) itemView.findViewById(R.id.dishImage);
        }

        @Override
        public void bind(Object data) {

        }
    }
}
