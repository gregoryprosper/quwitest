package com.example.quwitest.ui.screens.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quwitest.R;
import com.example.quwitest.data.api.model.chat.Channel;
import com.example.quwitest.databinding.FragmentChatBinding;
import com.example.quwitest.ui.adapters.ChatAdapter;
import com.example.quwitest.ui.screens.auth.LoginViewModel;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ChatViewModel chatViewModel;

    private ChatAdapter chatAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        chatAdapter = new ChatAdapter();
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupList();
        setupObservers();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupList() {
        binding.rvList.setAdapter(chatAdapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupObservers() {
        chatViewModel.channelError.observe(getViewLifecycleOwner(), s -> {
            Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show();
        });

        chatViewModel.channels.observe(getViewLifecycleOwner(), channels -> {
            chatAdapter.submitList(channels);
        });
    }
}