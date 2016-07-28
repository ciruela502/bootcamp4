package com.marta.bootcamp4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marta.bootcamp4.Presenter;
import com.marta.bootcamp4.R;
import com.marta.bootcamp4.api.UserApiService;
import com.marta.bootcamp4.userModel.ResultsList;
import com.squareup.picasso.Picasso;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity{//}  implements UserView{

    private static final String TAG = "main";
    private Subscription subscription;
    private UserApiService userApiService;
    private TextView textViewName;
    private ImageView imageView;
    private String name;
    private String surname;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userApiService = new UserApiService();
        textViewName = (TextView) findViewById(R.id.textview_name);
        imageView = (ImageView) findViewById(R.id.imageview_user);

        //presenter = new Presenter(this);
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
                .map(ResultsList::getUsers)
                .flatMap(Observable::from)
                .filter(user -> !user.getName().startsWith("Pupa"))
                .take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    name = user.getName();
                    surname = user.getSurname();
                    makeView();
                }, throwable -> {
                    throwable.printStackTrace();
                    Toast.makeText(getBaseContext(), R.string.error, Toast.LENGTH_SHORT).show();
                });

        Log.d(TAG, "loadUser: mam subscript");
    }

    private String getPictureURL(){
        return String.format("https://api.adorable.io/avatars/285/%s@%s", name, surname);
    }

    /*@Override
    public void showUserView(String name, String surname, String url) {
        //makeView(); ??
    }*/
    private void makeView() {
        textViewName.setText(String.format("%s %s", name, surname));
        getPictureURL();
        Picasso.with(this).load(getPictureURL()).into(imageView);
    }
}
