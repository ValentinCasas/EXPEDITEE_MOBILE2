package com.example.expeditee_mobile.ui.pendientes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expeditee_mobile.LoginActivity2ViewModel;
import com.example.expeditee_mobile.databinding.FragmentPendientesBinding;

public class PendientesFragment extends Fragment {

    private FragmentPendientesBinding binding;
    private PendientesViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PendientesViewModel galleryViewModel =
                new ViewModelProvider(this).get(PendientesViewModel.class);

        binding = FragmentPendientesBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(PendientesViewModel.class);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}