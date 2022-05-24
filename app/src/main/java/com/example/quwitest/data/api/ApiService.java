package com.example.quwitest.data.api;

import com.example.quwitest.QuwiApp;
import com.example.quwitest.data.api.model.chat.ChannelResponse;
import com.example.quwitest.data.api.model.auth.LoginRequest;
import com.example.quwitest.data.api.model.auth.LoginResponse;
import com.example.quwitest.services.UserService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private final QuwiApi quwiApi;

    private ApiService() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chain -> {
                    String token = UserService.getInstance(QuwiApp.getApplication()).getUserToken();
                    if (token != null && !token.isEmpty()) {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                        return chain.proceed(newRequest);
                    } else {
                        return chain.proceed(chain.request());
                    }
                }
            ).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.quwi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        quwiApi = retrofit.create(QuwiApi.class);
    }

    public static ApiService getInstance() {
        return InstanceHolder.instance;
    }

    private static final class InstanceHolder {
        static final ApiService instance = new ApiService();
    }

    public Call<LoginResponse> login(String email, String password) {
        return quwiApi.login(new LoginRequest(email, password));
    }

    public Call<ChannelResponse> getChannels() {
        return quwiApi.getChannels();
    }
}
