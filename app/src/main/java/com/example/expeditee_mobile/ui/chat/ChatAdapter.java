package com.example.expeditee_mobile.ui.chat;

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
import com.example.expeditee_mobile.models.Mensaje;
import com.example.expeditee_mobile.models.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private Context context;
    private List<Mensaje> mensajes;
    private LayoutInflater inflater;


    public ChatAdapter(Context context, List<Mensaje> mensajes, LayoutInflater inflater) {
        this.context = context;
        this.mensajes = mensajes;
        this.inflater = inflater;
    }

    public ChatAdapter(Context context, LayoutInflater inflater) {
        this.context = context;
        this.mensajes = mensajes;
        this.inflater = inflater;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_msg, parent, false);
        return new ChatAdapter.ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        String fechaTexto = mensajes.get(position).getFecha();

        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");

            Date fecha = formatoEntrada.parse(fechaTexto);
            String fechaFormateada = formatoSalida.format(fecha);

            holder.fecha.setText(fechaFormateada);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.nombre.setText(mensajes.get(position).getEmisor().getNombre() + " " + mensajes.get(position).getEmisor().getApellido());
        holder.descripcion.setText(mensajes.get(position).getDescripcion());


    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre,fecha,descripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombre = itemView.findViewById(R.id.tvNombre);
            fecha = itemView.findViewById(R.id.tvFecha);
            descripcion = itemView.findViewById(R.id.tvMsg);
        }


        @Override
        public void onClick(View v) {

        }
    }

}