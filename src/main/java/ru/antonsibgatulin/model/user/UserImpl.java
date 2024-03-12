package ru.antonsibgatulin.model.user;

import ru.antonsibgatulin.entity.user.UserData;

public class UserImpl implements IUser {
    private UserData userData;


    @Override
    public UserData getUser() {
        return userData;
    }

    @Override
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
