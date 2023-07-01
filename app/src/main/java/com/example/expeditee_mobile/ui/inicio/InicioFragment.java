package com.example.expeditee_mobile.ui.inicio;

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

import com.example.expeditee_mobile.databinding.FragmentInicioBinding;
import com.example.expeditee_mobile.models.Retroalimentacion;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.ui.pendientes.PendientesAdapter;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel mv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InicioViewModel homeViewModel =
                new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(InicioViewModel.class);
        View root = binding.getRoot();

        mv.getRetroalimentacionMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Retroalimentacion>>() {
            @Override
            public void onChanged(ArrayList<Retroalimentacion> retroalimentacion) {

                RecyclerView rv = binding.rvLista;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);
                InicioAdapter adapter = new InicioAdapter(getContext(), retroalimentacion, getLayoutInflater());
                rv.setAdapter(adapter);

            }
        });

        mv.obtenerRetroalimentaciones();

        return root;
    }


}