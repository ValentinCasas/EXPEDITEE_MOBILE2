package com.example.expeditee_mobile.ui.chat;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentContactosBinding;
import com.example.expeditee_mobile.databinding.FragmentPendientesBinding;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.ui.pendientes.PendientesAdapter;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

import java.util.ArrayList;

public class ContactosFragment extends Fragment {

    private ContactosViewModel mv;
    private FragmentContactosBinding binding;
    public static ContactosFragment newInstance() {
        return new ContactosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContactosBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(ContactosViewModel.class);
        View root = binding.getRoot();

        mv.getEmpleados().observe(getViewLifecycleOwner(), new Observer<ArrayList<Usuario>>() {
            @Override
            public void onChanged(ArrayList<Usuario> usuarios) {

                RecyclerView rv = binding.rvLista;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);
                ContactosAdapter adapter = new ContactosAdapter(getContext(), usuarios, getLayoutInflater());
                rv.setAdapter(adapter);

            }
        });

        mv.obtenerEmpleados();

        return root;
    }


}