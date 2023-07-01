package com.example.expeditee_mobile.ui.cobro;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentEfectivoBinding;
import com.example.expeditee_mobile.databinding.FragmentPendientesBinding;
import com.example.expeditee_mobile.models.Pedido;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.ui.pendientes.PendientesAdapter;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

import java.util.ArrayList;

public class EfectivoFragment extends Fragment {

    private EfectivoViewModel mv;
    private FragmentEfectivoBinding binding;
    private Usuario cliente;
    public static EfectivoFragment newInstance() {
        return new EfectivoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEfectivoBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(EfectivoViewModel.class);

        Bundle bundle = getArguments();
        cliente = (Usuario) bundle.getSerializable("cliente");


        mv.getPedidoMutable().observe(getViewLifecycleOwner(), new Observer<Pedido>() {
            @Override
            public void onChanged(Pedido pedido) {

                binding.tvMonto.setText("$ "+pedido.getMontoTotal()+"");

                mv.getEmpleadoMutable().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
                    @Override
                    public void onChanged(Usuario empleado) {

                        binding.tvMonto.setText("$ "+pedido.getMontoTotal()+"");
                        binding.btnMarcarPagado.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                mv.generarComprobante(pedido.getId(), pedido.getMontoTotal(), empleado.getNombre()+ " " +empleado.getApellido(), cliente.getNombre()+" "+cliente.getApellido(), cliente.getMail());
                                mv.actualizarEstadoPedido(pedido.getId());

                                binding.btnMarcarPagado.setText("PAGADO");
                                binding.btnMarcarPagado.setEnabled(false);
                            }
                        });

                    }
                });

                mv.getUsuario(pedido.getIdEmpleado());

            }

        });

        binding.btnMarcarEntregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("cliente", cliente);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_ubicacion, bundle);
            }
        });

        mv.obtenerPedido(cliente.getIdPedido());

        View root = binding.getRoot();
        return root;
    }



}