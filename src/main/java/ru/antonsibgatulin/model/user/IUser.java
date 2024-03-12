package ru.antonsibgatulin.model.user;

import ru.antonsibgatulin.entity.user.UserData;

public interface IUser {
    public UserData getUser();

    public void setUserData(UserData userData);
}
