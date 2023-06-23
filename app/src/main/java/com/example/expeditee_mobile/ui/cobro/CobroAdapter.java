package com.example.expeditee_mobile.ui.cobro;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.ui.pendientes.PendientesAdapter;

import java.util.List;

public class CobroAdapter extends RecyclerView.Adapter<CobroAdapter.ViewHolder>{

    private Context context;
    private List<Usuario> clientes;
    private LayoutInflater inflater;

    public CobroAdapter(Context context, List<Usuario> clientes, LayoutInflater inflater) {
        this.context = context;
        this.clientes = clientes;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public CobroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_cliente_cobro, parent, false);
        return new CobroAdapter.ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull CobroAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(clientes.get(position).getNombre());
        holder.direccion.setText(clientes.get(position).getDireccion());
        holder.telefono.setText(clientes.get(position).getTelefono() + "");
        //holder.nombre.setText(inmuebles.get(position).getPrecioInmueble() + "");
        //Glide.with(context).load(inmuebles.get(position).getImagen()).into(holder.fotoInmueble);


    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre, direccion, telefono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombre = itemView.findViewById(R.id.tvNombre);
            direccion = itemView.findViewById(R.id.tvDireccion);
            telefono = itemView.findViewById(R.id.tvTelefono);
        }

        @Override
        public void onClick(View v) {

        }

        /*@Override
        public void onClick(View v) {
            NavController navController = Navigation.findNavController(v);
            Usuario cliente = clientes.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable("cliente", cliente);
            navController.navigate(R.id.nav_ubicacion, bundle);
        }*/

    }

}
