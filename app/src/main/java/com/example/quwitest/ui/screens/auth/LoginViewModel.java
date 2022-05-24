package com.example.quwitest.ui.screens.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quwitest.data.api.ApiService;
import com.example.quwitest.data.api.model.auth.LoginResponse;
import com.example.quwitest.services.UserService;
import com.example.quwitest.util.SingleLiveEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private final SingleLiveEvent<String> _loginError = new SingleLiveEvent<>();
    public LiveData<String> loginError = _loginError;

    private final SingleLiveEvent<LoginResponse> _loginResult = new SingleLiveEvent<>();
    public LiveData<LoginResponse> loginResult = _loginResult;

    public void login(String email, String password) {
        Call<LoginResponse> response = ApiService.getInstance().login(email, password);
        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse data = response.body();
                    if (data != null) {
                        _loginResult.postValue(data);
                    }
                } else {
                    _loginError.postValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                _loginError.postValue(t.getLocalizedMessage());
            }
        });
    }
}
