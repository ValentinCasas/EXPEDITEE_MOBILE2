package com.example.expeditee_mobile.ui.cobro;

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
        Glide.with(context).load(clientes.get(position).getImagenFile()).into(holder.fotoCliente);


    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre, direccion, telefono;
        Button efectivo;
        ImageView mercadoPago;
        ImageView fotoCliente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombre);
            direccion = itemView.findViewById(R.id.tvDireccion);
            telefono = itemView.findViewById(R.id.tvTelefono);
            efectivo = itemView.findViewById(R.id.btnEfectivo);
            mercadoPago = itemView.findViewById(R.id.ivMercadoPago);
            fotoCliente = itemView.findViewById(R.id.ivCliente);

            efectivo.setOnClickListener(this);
            mercadoPago.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            NavController navController = Navigation.findNavController(v);

            Usuario cliente = clientes.get(getAdapterPosition());
            Pedido pedido = new Pedido();

            Bundle bundle = new Bundle();
            bundle.putSerializable("cliente", cliente);
            bundle.putSerializable("pedido", pedido);


            int buttonId = v.getId();
            if (buttonId == R.id.btnEfectivo) {
                navController.navigate(R.id.nav_efectivo, bundle);
            } else if (buttonId == R.id.ivMercadoPago) {
                navController.navigate(R.id.nav_mercadoPago, bundle);
            }
        }
    }


}
