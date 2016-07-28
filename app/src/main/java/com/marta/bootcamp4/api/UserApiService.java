package com.marta.bootcamp4.api;

import com.marta.bootcamp4.userModel.ResultsList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by marta on 28.07.2016.
 */
public class UserApiService {
    private static final String BASE_URL = "https://randomuser.me/";
    private static final String TAG = "api";
    private final UserApi userApi;

    public UserApiService() {
        this.userApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi.class);
    }

    public Observable<ResultsList> getUsersList(final int number) {
        return userApi.getUsersList(number);
    }
}

