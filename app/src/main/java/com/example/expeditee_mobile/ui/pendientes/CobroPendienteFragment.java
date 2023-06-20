package com.example.expeditee_mobile.ui.pendientes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentCobroPendienteBinding;

public class CobroPendienteFragment extends Fragment {

    private CobroPendienteViewModel mViewModel;
    private FragmentCobroPendienteBinding binding;
    public static CobroPendienteFragment newInstance() {
        return new CobroPendienteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCobroPendienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CobroPendienteViewModel.class);
        // TODO: Use the ViewModel
    }

}