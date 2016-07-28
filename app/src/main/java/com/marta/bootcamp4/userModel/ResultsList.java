package com.marta.bootcamp4.userModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marta on 28.07.2016.
 */
public class ResultsList {
    @SerializedName("results")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
