package com.example.expeditee_mobile.ui.cobro;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentMercadoPagoBinding;
import com.example.expeditee_mobile.databinding.FragmentPendientesBinding;
import com.example.expeditee_mobile.models.Pedido;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

public class MercadoPagoFragment extends Fragment {

    private MercadoPagoViewModel mv;
    private FragmentMercadoPagoBinding binding;
    private Usuario cliente;

    public static MercadoPagoFragment newInstance() {
        return new MercadoPagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMercadoPagoBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(MercadoPagoViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        cliente = (Usuario) bundle.getSerializable("cliente");


        mv.getPedidoMutable().observe(getViewLifecycleOwner(), new Observer<Pedido>() {
            @Override
            public void onChanged(Pedido pedido) {

                //pedido.getMontoTotal()

            }
        });

        mv.obtenerPedido(cliente.getIdPedido());

        return root;
    }



}