package com.marta.bootcamp4.userModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marta on 28.07.2016.
 */
public class Picture {

    @SerializedName("large")
    private String large;
    @SerializedName("medium")
    private String medium;
    @SerializedName("thumbnail")
    private String thumbnail;

}
