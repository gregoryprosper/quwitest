package com.example.quwitest.data.api.model.auth;

public class AppInit {
    private final User user;

    public AppInit(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
