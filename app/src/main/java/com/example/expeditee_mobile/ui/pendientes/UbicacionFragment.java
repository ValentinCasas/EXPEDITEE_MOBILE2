package com.example.expeditee_mobile.ui.pendientes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentUbicacionBinding;
import com.example.expeditee_mobile.models.Usuario;

public class UbicacionFragment extends Fragment {

    private UbicacionViewModel mViewModel;
    private FragmentUbicacionBinding binding;
    private Usuario cliente;
    public static UbicacionFragment newInstance() {
        return new UbicacionFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUbicacionBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(UbicacionViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        cliente = (Usuario) bundle.getSerializable("cliente");

        binding.tvNombre.setText(cliente.getNombre());
        binding.tvDireccion.setText(cliente.getDireccion());
        binding.tvTelefono.setText(String.valueOf(cliente.getTelefono()));

        binding.btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cliente", cliente);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_cobroPendiente, bundle);
            }
        });




        return root;
    }


}