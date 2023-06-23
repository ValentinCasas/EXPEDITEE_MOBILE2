package com.example.expeditee_mobile.ui.cobro;

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

import com.example.expeditee_mobile.databinding.FragmentCobroBinding;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.ui.pendientes.PendientesAdapter;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

import java.util.ArrayList;

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

        mv.getPendientes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Usuario>>() {
            @Override
            public void onChanged(ArrayList<Usuario> usuarios) {

                RecyclerView rv = binding.rvLista;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);
                CobroAdapter adapter = new CobroAdapter(getContext(), usuarios, getLayoutInflater());
                rv.setAdapter(adapter);

            }
        });

        mv.obtenerClientesPendientes();

        return root;
    }

}