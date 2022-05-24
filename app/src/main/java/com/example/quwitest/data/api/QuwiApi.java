package com.example.quwitest.data.api;

import com.example.quwitest.data.api.model.chat.ChannelResponse;
import com.example.quwitest.data.api.model.auth.LoginRequest;
import com.example.quwitest.data.api.model.auth.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface QuwiApi {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("chat-channels")
    Call<ChannelResponse> getChannels();
}
