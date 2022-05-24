package com.example.quwitest.data.api.model.auth;


import java.util.ArrayList;

public class LoginResponse {
    private final String token;
    private final AppInit app_init;

    public LoginResponse(
            String token,
            AppInit app_init
    ) {
        this.token = token;
        this.app_init = app_init;
    }


    // Getter Methods

    public String getToken() {
        return token;
    }

    public AppInit getAppInit() {
        return app_init;
    }
}

