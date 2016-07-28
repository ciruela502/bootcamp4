package com.marta.bootcamp4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marta.bootcamp4.R;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements MainView {

    private TextView textViewName;
    private ImageView imageView;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewName = (TextView) findViewById(R.id.textview_name);
        imageView = (ImageView) findViewById(R.id.imageview_user);

        presenter = new MainPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.loadUser();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void showUserView(String name, String surname, String url) {
        textViewName.setText(String.format("%s %s", name, surname));
        Picasso.with(this).load(url).into(imageView);
    }

    @Override
    public void makeToastError() {
        Toast.makeText(getBaseContext(), R.string.error, Toast.LENGTH_SHORT).show();
    }

}
