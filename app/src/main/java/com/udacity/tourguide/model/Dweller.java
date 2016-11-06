package com.udacity.tourguide.model;

/**
 * Created by geovani on 05/11/16.
 */

public class Dweller {

    public final String mID;
    private String mName;
    private int mImage;
    private int mRating; // [1, 5] estrelas
    private String mDescription;


    public Dweller(String id, String name, int image, int rating, String description) {
        mID          = id;
        mName        = name;
        mImage       = image;
        mRating      = rating;
        mDescription = description;
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }


    public int getRating() {
        return mRating;
    }


    public void setRating(int rating) {
        mRating = rating;
    }


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }



}
