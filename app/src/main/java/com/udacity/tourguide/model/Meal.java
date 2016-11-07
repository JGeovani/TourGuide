package com.udacity.tourguide.model;

/**
 * Created by geovani on 06/11/16.
 */

public class Meal {

    public final String mID;
    private String mName;
    private int mImage;
    private String mDescription;


    public Meal(String ID, String name, int image, String description) {
        mID          = ID;
        mName        = name;
        mImage       = image;
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


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
