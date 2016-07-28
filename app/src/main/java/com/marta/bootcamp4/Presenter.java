package com.marta.bootcamp4;

/**
 * Created by marta on 28.07.2016.
 */
public class Presenter implements UserView{

    private UserView userView;
    private String name;
    private String surname;

    public Presenter(UserView userView) {
        this.userView = userView;
    }

    @Override
    public void showUserView() {

    }
    private String getPictureURL(){
        return String.format("https://api.adorable.io/avatars/285/%s@%s", name, surname);
    }
}
