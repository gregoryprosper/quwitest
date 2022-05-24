package com.example.quwitest.data.api.model.chat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class Channel {
    private final float id;
    private final LastMessage message_last;
    private final CustomInfo custom_info;

    public Channel(
            float id,
            LastMessage message_last,
            CustomInfo custom_info
    ) {
        this.id = id;
        this.message_last = message_last;
        this.custom_info = custom_info;
    }


    // Getter Methods

    public float getId() {
        return id;
    }

    public LastMessage getLastMessage() {
        return message_last;
    }

    public CustomInfo getCustomInfo() {
        return custom_info;
    }

    public static final class DIFF_UTIL extends DiffUtil.ItemCallback<Channel> {

        @Override
        public boolean areItemsTheSame(@NonNull Channel oldItem, @NonNull Channel newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Channel oldItem, @NonNull Channel newItem) {
            return oldItem.id == newItem.id;
        }
    }
}

