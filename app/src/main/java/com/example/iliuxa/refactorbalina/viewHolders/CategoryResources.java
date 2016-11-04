package com.example.iliuxa.refactorbalina.viewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.pojo.Category;
import com.squareup.picasso.Picasso;


public class CategoryResources extends MyViewHolder {
    private TextView mCategoryName;
    private ImageView mCategoryImage;
    private Context mContext;

    public CategoryResources(View itemView,Context context) {
        super(itemView);
        mContext = context;
        mCategoryName = (TextView)itemView.findViewById(R.id.categoryNameText);
        mCategoryImage = (ImageView)itemView.findViewById(R.id.imageCategory);
    }
    @Override
    public void bind(Object data) {
        mCategoryName.setText(((Category)data).getName());
        Picasso.with(mContext)
                .load(mContext.getResources().getIdentifier("id_"+((Category) data).getIdCategory(),"drawable",mContext.getPackageName()))
                .resize(400,300)
                .into(mCategoryImage);
    }
}

