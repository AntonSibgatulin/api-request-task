package ru.antonsibgatulin.entity.user;

import java.time.Instant;

public class UserData {
    private String id;

    public UserData(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
