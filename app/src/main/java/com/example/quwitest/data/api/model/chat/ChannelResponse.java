package com.example.quwitest.data.api.model.chat;

import java.util.ArrayList;

public class ChannelResponse {
    private final ArrayList<Channel> channels;

    public ChannelResponse(
            ArrayList<Channel> channels
    ) {
        this.channels = channels;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }
}

