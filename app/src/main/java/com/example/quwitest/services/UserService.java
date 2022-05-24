package com.example.quwitest.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.quwitest.data.api.ApiService;

public class UserService {
    private static volatile UserService instance;

    private static final String KEY_USER_TOKEN = "com.example.quwitest.user_token";
    private static final String KEY_USER_ID = "com.example.quwitest.user_id";

    private final SharedPreferences sharedPreferences;

    private int userId;
    private String userToken;

    public UserService(Context context) {
        sharedPreferences = context.getSharedPreferences("user_service", Context.MODE_PRIVATE);
        userToken = sharedPreferences.getString(KEY_USER_TOKEN, null);
        userId = sharedPreferences.getInt(KEY_USER_ID, -1);
    }

    public static UserService getInstance(Context context) {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
        sharedPreferences.edit().putString(KEY_USER_TOKEN, userToken).apply();
    }

    public void setUserId(int userId) {
        this.userId = userId;
        sharedPreferences.edit().putInt(KEY_USER_ID, userId).apply();
    }

    public String getUserToken() {
        return userToken;
    }

    public int getUserId() {
        return userId;
    }

    public Boolean isLoggedIn() {
        return userToken != null && !userToken.isEmpty();
    }
}
