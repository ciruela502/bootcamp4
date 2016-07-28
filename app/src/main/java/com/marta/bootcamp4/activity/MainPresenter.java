package com.marta.bootcamp4.activity;

import com.marta.bootcamp4.api.UserApiService;
import com.marta.bootcamp4.userModel.ResultsList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter {

    private MainView mainView;
    private String name;
    private String surname;
    private Subscription subscription;
    private UserApiService userApiService;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        userApiService = new UserApiService();
    }

    private String getPictureURL() {
        return String.format("https://api.adorable.io/avatars/285/%s@%s", name, surname);
    }

    public void loadUser() {

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
                    showToastError();
                });
    }

    private void makeView() {
        mainView.showUserView(name, surname, getPictureURL());
    }

    public void unsubscribe() {
        if (subscription != null) subscription.unsubscribe();
    }

    public void showToastError() {
        mainView.makeToastError();
    }
}
