package com.example.iliuxa.refactorbalina.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(View itemView) {
        super(itemView);
    }

    abstract public void bind(Object data);
}
