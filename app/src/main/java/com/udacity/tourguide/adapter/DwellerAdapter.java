package com.udacity.tourguide.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.udacity.tourguide.R;
import com.udacity.tourguide.model.Dweller;

import java.util.List;

/**
 * Created by geovani on 06/11/16.
 */


public class DwellerAdapter extends RecyclerView.Adapter<DwellerAdapter.MyViewHolder> {

    private final static String TAG = DwellerAdapter.class.getSimpleName();

    private Context mContext;
    private List<Dweller> mDwellers;


    public DwellerAdapter(Context context, List<Dweller> dwellers) {
        mContext  = context;
        mDwellers = dwellers;
    }


    @Override
    public DwellerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_dweller, parent, false);
        return new DwellerAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(DwellerAdapter.MyViewHolder holder, int position) {
        Dweller dweller = mDwellers.get(position);
        holder.mNameText.setText(dweller.getName());
        holder.mImageView.setImageResource(dweller.getImage());
        holder.mRatingBar.setNumStars(dweller.getRating());
        holder.mDescText.setText(dweller.getDescription());
        startActivityOnClick(holder);
    }


    private void startActivityOnClick(final MyViewHolder holder) {
        final Bundle params = new Bundle();
        params.putString("dweller", holder.mNameText.getText().toString());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "adapter position: " + holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDwellers.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mNameText;
        public ImageView mImageView;
        public RatingBar mRatingBar;
        public TextView mDescText;
        public View mView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            mNameText  = (TextView) itemView.findViewById(R.id.nameText);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            mDescText  = (TextView) itemView.findViewById(R.id.descriptionText);
            mView      = itemView;
        }
    }



}
