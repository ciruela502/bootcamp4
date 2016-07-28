package com.marta.bootcamp4.userModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marta on 28.07.2016.
 */
public class User {

    @SerializedName("name")
    private Name name;
    @SerializedName("picture")
    private Picture picture;

    public String getName(){
        return name.first  + " " + name.last;
    }
}

