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
import com.example.expeditee_mobile.models.Usuario;

public class CobroPendienteFragment extends Fragment {

    private CobroPendienteViewModel mViewModel;
    private FragmentCobroPendienteBinding binding;
    private Usuario cliente;
    public static CobroPendienteFragment newInstance() {
        return new CobroPendienteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCobroPendienteBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(CobroPendienteViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        cliente = (Usuario) bundle.getSerializable("cliente");


        binding.tvNombre.setText(cliente.getNombre());
        binding.tvDireccion.setText(cliente.getDireccion());
        binding.tvTelefono.setText(String.valueOf(cliente.getTelefono()));

        return root;
    }



}