package com.example.expeditee_mobile.ui.pendientes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentCobroPendienteBinding;
import com.example.expeditee_mobile.models.Usuario;

public class CobroPendienteFragment extends Fragment {

    private CobroPendienteViewModel mv;
    private FragmentCobroPendienteBinding binding;
    private Usuario cliente;
    public static CobroPendienteFragment newInstance() {
        return new CobroPendienteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCobroPendienteBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(CobroPendienteViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        cliente = (Usuario) bundle.getSerializable("cliente");


        binding.tvNombre.setText(cliente.getNombre() + " " + cliente.getApellido());
        binding.tvDireccion.setText(cliente.getDireccion());
        binding.tvTelefono.setText(String.valueOf(cliente.getTelefono()));

        mv.getImagenMutable().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap imageBytes) {
                if (imageBytes != null) {

                    binding.ivCliente.setImageBitmap(imageBytes);
                }
            }
        });

        mv.obtenerImagenUsuario(cliente.getId());

        binding.btnEfectivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cliente", cliente);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_efectivo, bundle);
            }
        });

        binding.ivMercadoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cliente", cliente);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_mercadoPago, bundle);
            }
        });

        return root;
    }



}