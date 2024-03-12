package ru.antonsibgatulin.entity.user;

import java.time.Instant;

public class UserData {
    private String id;
    private volatile Instant lastRequestTime;


    public UserData(String id, Instant lastRequestTime) {
        this.id = id;
        this.lastRequestTime = lastRequestTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getLastRequestTime() {
        return lastRequestTime;
    }

    public void setLastRequestTime(Instant lastRequestTime) {
        this.lastRequestTime = lastRequestTime;
    }
}
