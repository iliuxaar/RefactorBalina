package com.example.iliuxa.refactorbalina.viewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.pojo.Offer;
import com.squareup.picasso.Picasso;


public class OfferResources extends MyViewHolder {
    private TextView mOfferName;
    private ImageView mOfferImage;
    private TextView mOfferPrice;
    private TextView mOfferDescription;
    private Context mContext;


    public OfferResources(View itemView, Context context) {
        super(itemView);
        mContext = context;
        mOfferName = (TextView)itemView.findViewById(R.id.offerNameText);
        mOfferPrice = (TextView)itemView.findViewById(R.id.priceText);
        mOfferDescription = (TextView)itemView.findViewById(R.id.descriptionText);
        mOfferImage = (ImageView) itemView.findViewById(R.id.offerImage);
    }

    @Override
    public void bind(Object data) {
        mOfferName.setText(((Offer)data).getName());
        mOfferPrice.setText(((Offer)data).getPrice());
        mOfferDescription.setText(((Offer)data).getDescription());
            Picasso.with(mContext)
                    .load(((Offer)data).getPicture())
                    .error(R.drawable.downloading)
                    .resize(400, 300)
                    .into(mOfferImage);

    }
}


