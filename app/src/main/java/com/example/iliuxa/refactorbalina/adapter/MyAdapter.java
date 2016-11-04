package com.example.iliuxa.refactorbalina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.viewHolders.CategoryResources;
import com.example.iliuxa.refactorbalina.viewHolders.OfferResources;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_CATEGORY = 1;
    private final static int TYPE_DISHES = 2;
    private int mResource;
    private ArrayList mData;
    private Context mContext;

    public MyAdapter(Context context, int resource, ArrayList data) {
        this.mContext = context;
        this.mResource = resource;
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CATEGORY:
                return new CategoryResources(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_category_list_item, parent, false),mContext);
            case TYPE_DISHES:
                return new OfferResources(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.simple_offer_list_item, parent, false),mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_CATEGORY:
                ((CategoryResources) holder).bind(mData.get(position));
                break;
            case TYPE_DISHES:
                ((OfferResources) holder).bind(mData.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mResource == R.layout.simple_category_list_item) {
            return TYPE_CATEGORY;
        } else if (mResource == R.layout.simple_offer_list_item) {
            return TYPE_DISHES;
        } else return 0;
    }

    public Object getClickedItem(int position) {
        return mData.get(position);
    }
}


