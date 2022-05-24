package com.example.quwitest.ui.screens.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.quwitest.R;
import com.example.quwitest.databinding.FragmentLoginBinding;
import com.example.quwitest.services.UserService;
import com.example.quwitest.ui.activities.MainActivity;
import com.example.quwitest.ui.screens.chat.ChatFragment;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupObservers();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserService.getInstance(this.getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), MainActivity.class));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupObservers() {
        binding.loginBtn.setOnClickListener(view -> {
            String email = binding.emailEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();
            if (!email.isEmpty() && !password.isEmpty()) {
                loginViewModel.login(email, password);
            } else {
                Toast.makeText(requireContext(),
                        "Email & password are required",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        loginViewModel.loginError.observe(getViewLifecycleOwner(), s -> {
            Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show();
        });

        loginViewModel.loginResult.observe(getViewLifecycleOwner(), response -> {
            if (!response.getToken().isEmpty()) {
                UserService.getInstance(
                        LoginFragment.this.getContext()).setUserToken(response.getToken()
                );
                UserService.getInstance(
                        LoginFragment.this.getContext()
                ).setUserId(response.getAppInit().getUser().getId());
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
    }
}