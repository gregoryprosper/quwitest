package com.example.quwitest.ui.screens.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quwitest.data.api.ApiService;
import com.example.quwitest.data.api.model.chat.Channel;
import com.example.quwitest.data.api.model.chat.ChannelResponse;
import com.example.quwitest.util.SingleLiveEvent;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends ViewModel {

    private final SingleLiveEvent<String> _channelError = new SingleLiveEvent<>();
    public LiveData<String> channelError = _channelError;

    private final SingleLiveEvent<Boolean> _loading = new SingleLiveEvent<>();
    public LiveData<Boolean> loading = _loading;

    private final MutableLiveData<ArrayList<Channel>> _channels = new MutableLiveData<>();
    public LiveData<ArrayList<Channel>> channels = _channels;

    public ChatViewModel() {
        _loading.postValue(true);
        Call<ChannelResponse> call = ApiService.getInstance().getChannels();
        call.enqueue(new Callback<ChannelResponse>() {
            @Override
            public void onResponse(Call<ChannelResponse> call, Response<ChannelResponse> response) {
                if (response.isSuccessful()) {
                    ChannelResponse data = response.body();
                    if (data != null) {
                        ArrayList<Channel> channels = data.getChannels();
                        channels.removeIf(s -> s.getLastMessage() == null);
                        _channels.postValue(channels);
                        _loading.postValue(false);
                    }
                } else {
                    _channelError.postValue(response.message());
                    _loading.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<ChannelResponse> call, Throwable t) {
                _channelError.postValue(t.getLocalizedMessage());
                _loading.postValue(false);
            }
        });
    }
}
