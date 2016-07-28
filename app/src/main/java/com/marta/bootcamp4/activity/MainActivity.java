package com.marta.bootcamp4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.marta.bootcamp4.R;
import com.marta.bootcamp4.api.UserApiService;
import com.marta.bootcamp4.userModel.ResultsList;
import com.marta.bootcamp4.userModel.User;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main";
    private Subscription subscription;
    private UserApiService userApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userApiService = new UserApiService();


    }

    @Override
    protected void onStart() {
        super.onStart();
        loadUser();
        Log.d(TAG, "onStart: zrobilo loadUser");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription != null) subscription.unsubscribe();
    }

    private void loadUser() {
        subscription = userApiService.getUsersList(5)
                .map(new Func1<ResultsList, List<User>>() {
                    @Override
                    public List<User> call(ResultsList resultsList) {
                        return resultsList.getUsers();
                    }
                }) //mam liste usero
                .flatMap(new Func1<List<User>, Observable<? extends User>>() {
                    @Override
                    public Observable<? extends User> call(List<User> userList) {
                        return Observable.from(userList);
                    }
                })
                //.take(1)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        Log.d(TAG, "onNext: " + users.get(0).getName());
                    }
                } , new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
                   /* @Override
                    public void onCompleted() {
                        //no-op
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<User> users) {
                        Log.d(TAG, "onNext: " + users.get(0).getName());
                    }
                });*/

        Log.d(TAG, "loadUser: mam subscript");
    }

}
