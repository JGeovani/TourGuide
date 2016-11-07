package com.udacity.tourguide.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.tourguide.R;
import com.udacity.tourguide.model.Meal;

import java.util.List;


/**
 * Created by geovani on 06/11/16.
 */


public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder> {

    private final static String TAG = MealAdapter.class.getSimpleName();

    private Context mContext;
    private List<Meal> mMeals;


    public MealAdapter(Context context, List<Meal> meals) {
        mContext = context;
        mMeals   = meals;
    }


    @Override
    public MealAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_meal, parent, false);
        return new MealAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MealAdapter.MyViewHolder holder, int position) {
        Meal meal = mMeals.get(position);
        holder.mNameText.setText(meal.getName());
        //
        holder.mImageView.setImageResource(meal.getImage());
        //
        holder.mDescText.setText(meal.getDescription());
        //
        startActivityOnClick(holder);
    }


    private void startActivityOnClick(final MyViewHolder holder) {
        final Bundle params = new Bundle();
        params.putString("meal", holder.mNameText.getText().toString());
        //
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "adapter position: " + holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mMeals.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mNameText;
        public ImageView mImageView;
        public TextView mDescText;
        public View mView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            mNameText  = (TextView) itemView.findViewById(R.id.nameText);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mDescText  = (TextView) itemView.findViewById(R.id.descriptionText);
            mView      = itemView;
        }
    }



}
