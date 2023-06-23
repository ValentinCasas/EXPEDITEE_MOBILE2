package com.example.expeditee_mobile.ui.pendientes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expeditee_mobile.LoginActivity2ViewModel;
import com.example.expeditee_mobile.databinding.FragmentPendientesBinding;
import com.example.expeditee_mobile.models.Usuario;

import java.util.ArrayList;

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

        mv.getPendientes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Usuario>>() {
            @Override
            public void onChanged(ArrayList<Usuario> usuarios) {

                RecyclerView rv = binding.rvLista;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);
                PendientesAdapter adapter = new PendientesAdapter(getContext(), usuarios, getLayoutInflater());
                rv.setAdapter(adapter);

            }
        });



        mv.obtenerClientesPendientes();

        return root;
    }

}