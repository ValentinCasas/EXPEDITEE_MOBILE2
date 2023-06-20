package com.example.expeditee_mobile.ui.cobro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expeditee_mobile.databinding.FragmentCobroBinding;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

public class CobroFragment extends Fragment {

    private FragmentCobroBinding binding;
    private CobroViewModel mv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CobroViewModel slideshowViewModel =
                new ViewModelProvider(this).get(CobroViewModel.class);

        binding = FragmentCobroBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(CobroViewModel.class);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}