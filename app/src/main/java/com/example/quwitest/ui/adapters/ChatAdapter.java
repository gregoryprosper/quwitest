package com.example.quwitest.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quwitest.R;
import com.example.quwitest.data.api.model.chat.Channel;
import com.example.quwitest.data.api.model.chat.CustomInfo;
import com.example.quwitest.data.api.model.chat.LastMessage;
import com.example.quwitest.data.api.model.chat.User;
import com.example.quwitest.databinding.ItemChannelBinding;
import com.example.quwitest.services.UserService;
import com.example.quwitest.util.DateUtil;

public class ChatAdapter extends ListAdapter<Channel, ChatAdapter.ViewHolder> {

    public ChatAdapter() {
        super(new Channel.DIFF_UTIL());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(ItemChannelBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Channel channel = getItem(position);
        holder.bind(channel);
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        ItemChannelBinding binding;
        Context context;

        public ViewHolder(@NonNull ItemChannelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.context = binding.getRoot().getContext();
        }

        public void bind(Channel channel) {
            LastMessage lastMessage = channel.getLastMessage();
            CustomInfo customInfo = channel.getCustomInfo();
            if (lastMessage != null) {
                User user = lastMessage.getUser();
                if (customInfo != null) {
                    binding.nameTxt.setText(customInfo.getName());
                    Glide.with(binding.userImage)
                            .load(customInfo.getLogoUrl())
                            .circleCrop()
                            .into(binding.userImage);
                } else {
                    binding.nameTxt.setText(user.getName());
                    Glide.with(binding.userImage)
                            .load(user.getAvatarUrl())
                            .circleCrop()
                            .into(binding.userImage);
                }
                binding.messageTxt.setText(lastMessage.getSnippet());
                if (lastMessage.getUserId() == UserService.getInstance(context).getUserId()) {
                    binding.senderText.setText(R.string.sender_you);
                } else {
                    binding.senderText.setText(user.getName() + ":");
                }
                binding.dateTxt.setText(DateUtil.format(lastMessage.getCreatedDate()));
            }
        }
    }
}
