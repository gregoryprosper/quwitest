package com.example.quwitest.data.api.model.chat;

public class CustomInfo {
    private final String name;
    private final String logo_url;
    private final String hash;
    private final Boolean is_author;


    public CustomInfo(String name, String logo_url, String hash, Boolean is_author) {
        this.name = name;
        this.logo_url = logo_url;
        this.hash = hash;
        this.is_author = is_author;
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logo_url;
    }

    public String getHash() {
        return hash;
    }

    public Boolean getIs_author() {
        return is_author;
    }
}
