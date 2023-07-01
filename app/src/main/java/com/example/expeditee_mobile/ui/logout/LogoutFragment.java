package com.example.expeditee_mobile.ui.logout;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentLogoutBinding;
import com.example.expeditee_mobile.databinding.FragmentPendientesBinding;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mv;
    private FragmentLogoutBinding binding;
    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(LogoutViewModel.class);
        View root = binding.getRoot();

        mv.mostrarDialogoDeConfirmacion(this);

        return root;
    }



}