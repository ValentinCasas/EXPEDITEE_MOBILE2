package com.example.expeditee_mobile.ui.chat;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentChatBinding;
import com.example.expeditee_mobile.databinding.FragmentPendientesBinding;
import com.example.expeditee_mobile.models.Mensaje;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class ChatFragment extends Fragment {

    private ChatViewModel mv;
    private FragmentChatBinding binding;
    private Usuario empleado;
    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(ChatViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        empleado = (Usuario) bundle.getSerializable("empleado");


        mv.getUsuarioMutable().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {

                mv.getMensajes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Mensaje>>() {
                    @Override
                    public void onChanged(ArrayList<Mensaje> mensajes) {
                        RecyclerView rv = binding.rvLista;
                        GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                        rv.setLayoutManager(grilla);

                        ArrayList<Mensaje> mensajesInvertidos = new ArrayList<>(mensajes);
                        Collections.reverse(mensajesInvertidos);

                        ChatAdapter adapter = new ChatAdapter(getContext(), mensajesInvertidos, getLayoutInflater());
                        rv.setAdapter(adapter);
                    }
                });

                binding.btnEnviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mv.enviarMensaje(usuario.getId(), empleado.getId(), binding.etDescripcion.getText().toString());
                        binding.etDescripcion.setText("");
                    }
                });

                mv.obtenerMensajes(usuario.getId() ,empleado.getId());

            }
        });

        mv.getMensajesNow().observe(getViewLifecycleOwner(), new Observer<ArrayList<Mensaje>>() {
            @Override
            public void onChanged(ArrayList<Mensaje> mensajes) {
                // Actualizar la lista de mensajes en el adaptador
                RecyclerView rv = binding.rvLista;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);

                ArrayList<Mensaje> mensajesInvertidos = new ArrayList<>(mensajes);
                Collections.reverse(mensajesInvertidos);

                ChatAdapter adapter = new ChatAdapter(getContext(), mensajesInvertidos, getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });


        mv.obtenerUsuario();

        return root;
    }


}