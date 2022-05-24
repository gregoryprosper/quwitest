package com.example.quwitest.data.api.model.chat;

public class User {
    private final String name;
    private final String avatar_url;

    public User(String name, String avatar_url) {
        this.name = name;
        this.avatar_url = avatar_url;
    }

    // Getter Methods

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }
}
