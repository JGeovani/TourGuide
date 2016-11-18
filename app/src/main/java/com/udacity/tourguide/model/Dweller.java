package com.udacity.tourguide.model;

/**
 * Created by geovani on 05/11/16.
 */

public class Dweller {

    public final String mID;
    private String mName;
    private int mImage;
    private int mRating;
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


    public int getRating() {
        return mRating;
    }


    public String getDescription() {
        return mDescription;
    }


}
