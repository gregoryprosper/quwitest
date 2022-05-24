package com.example.quwitest.data.api.model.chat;

public class LastMessage {
    private final float id;
    private final float id_user;
    private final String dta_create;
    private final String text;
    private final String snippet;
    private final User user;

    public LastMessage(
            float id,
            float id_user,
            float id_channel,
            String dta_create,
            float is_read,
            boolean is_starred,
            boolean is_edited,
            String text,
            String snippet,
            String reply_on,
            String file,
            User user
    ) {
        this.id = id;
        this.id_user = id_user;
        this.dta_create = dta_create;
        this.text = text;
        this.snippet = snippet;
        this.user = user;
    }

    // Getter Methods

    public float getId() {
        return id;
    }

    public float getUserId() {
        return id_user;
    }


    public String getCreatedDate() {
        return dta_create;
    }

    public String getText() {
        return text;
    }

    public String getSnippet() {
        return snippet;
    }

    public User getUser() {
        return user;
    }
}
