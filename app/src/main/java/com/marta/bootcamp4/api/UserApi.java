package com.marta.bootcamp4.api;

import com.marta.bootcamp4.userModel.ResultsList;

import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marta on 28.07.2016.
 */
public interface UserApi {
    @GET("api")
    Observable<ResultsList> getUsersList(@Query("results") int number);
}
