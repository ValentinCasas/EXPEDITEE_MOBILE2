package com.example.expeditee_mobile.ui.inicio;

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
import com.example.expeditee_mobile.models.Retroalimentacion;
import com.example.expeditee_mobile.models.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.ViewHolder>{

    private Context context;
    private List<Retroalimentacion> retroalimentacion;
    private LayoutInflater inflater;

    public InicioAdapter(Context context, List<Retroalimentacion> retroalimentacion, LayoutInflater inflater) {
        this.context = context;
        this.retroalimentacion = retroalimentacion;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public InicioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_retroalimentacion, parent, false);
        return new InicioAdapter.ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull InicioAdapter.ViewHolder holder, int position) {

        String fechaTexto = retroalimentacion.get(position).getFechaEnvio();

        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");

            Date fecha = formatoEntrada.parse(fechaTexto);
            String fechaFormateada = formatoSalida.format(fecha);

            holder.fecha.setText(fechaFormateada);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.nombre.setText(retroalimentacion.get(position).getUsuario().getNombre() +" "+ retroalimentacion.get(position).getUsuario().getApellido() );
        holder.descripcion.setText(retroalimentacion.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {
        return retroalimentacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre, fecha, descripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombre = itemView.findViewById(R.id.tvNombre);
            fecha = itemView.findViewById(R.id.tvFecha);
            descripcion = itemView.findViewById(R.id.tvDescripcion);

        }

        @Override
        public void onClick(View v) {

        }

    }


}
