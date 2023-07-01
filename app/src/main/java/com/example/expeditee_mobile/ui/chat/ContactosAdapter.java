package com.example.expeditee_mobile.ui.chat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.models.Pedido;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.ui.pendientes.PendientesAdapter;

import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ViewHolder>{

    private Context context;
    private List<Usuario> empleados;
    private LayoutInflater inflater;

    public ContactosAdapter(Context context, List<Usuario> empleados, LayoutInflater inflater) {
        this.context = context;
        this.empleados = empleados;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public ContactosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_contacto, parent, false);
        return new ContactosAdapter.ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactosAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(empleados.get(position).getNombre());
        Glide.with(context).load(empleados.get(position).getImagenFile()).into(holder.fotoEmpleado);
    }

    @Override
    public int getItemCount() {
        return empleados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre;
        Button btnChat;
        ImageView fotoEmpleado;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombre = itemView.findViewById(R.id.tvNombre);
            btnChat = itemView.findViewById(R.id.btnIrAChat);
            fotoEmpleado = itemView.findViewById(R.id.ivEmpleado);

            btnChat.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            NavController navController = Navigation.findNavController(v);

            Usuario empleado = empleados.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable("empleado", empleado);

            int buttonId = v.getId();
            if (buttonId == R.id.btnIrAChat) {
                navController.navigate(R.id.nav_chat, bundle);
            }
        }

    }

}

