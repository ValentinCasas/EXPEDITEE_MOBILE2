package com.example.expeditee_mobile.ui.chat;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.expeditee_mobile.MainActivity;
import com.example.expeditee_mobile.models.Mensaje;
import com.example.expeditee_mobile.models.Pedido;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.request.ApiClientRetrofit;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Mensaje>> mensajesMutable;
    private MutableLiveData<ArrayList<Mensaje>> mensajesMutableNow;
    private MutableLiveData<Usuario> usuarioMutable;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioMutable() {
        if (usuarioMutable == null) {
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }
    public LiveData<ArrayList<Mensaje>> getMensajes() {
        if (mensajesMutable == null) {
            mensajesMutable = new MutableLiveData<>();
        }
        return mensajesMutable;
    }

    public LiveData<ArrayList<Mensaje>> getMensajesNow() {
        if (mensajesMutableNow == null) {
            mensajesMutableNow = new MutableLiveData<>();
        }
        return mensajesMutableNow;
    }

    public void obtenerMensajes(int idEmisor ,int idReceptor) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
        Call<ArrayList<Mensaje>> call = end.obtenerMensajes(token, idEmisor, idReceptor);

        call.enqueue(new Callback<ArrayList<Mensaje>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Mensaje>> call, @NonNull Response<ArrayList<Mensaje>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Mensaje> msj = response.body();
                    if (msj != null) {

                        mensajesMutable.setValue(msj);
                    }
                } else {
                    String errorMessage = "Error: " + response.code() + " - " + response.message();
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Mensaje>> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener los mensajes" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void enviarMensaje(int idEmisor, int idReceptor, String descripcion) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
        Call<Mensaje> call = end.enviarMensaje(token, idEmisor, idReceptor, descripcion);

        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(@NonNull Call<Mensaje> call, @NonNull Response<Mensaje> response) {
                if (response.isSuccessful()) {
                    Mensaje mensajeNow = response.body();
                    if (mensajeNow != null) {
                        agregarMensaje(mensajeNow);
                    }
                } else {
                    String errorMessage = "Error: " + response.code() + " - " + response.message();
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Mensaje> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al enviar el mensaje: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void obtenerUsuario() {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        if (token.isEmpty()) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            return;
        }

        ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
        Call<Usuario> call = end.obtenerPerfil(token);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Usuario usuario = response.body();
                        usuarioMutable.setValue(usuario);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void agregarMensaje(Mensaje mensaje) {
        ArrayList<Mensaje> mensajes = mensajesMutable.getValue();
        if (mensajes != null) {
            mensajes.add(mensaje);
            mensajesMutableNow.setValue(mensajes);
        }
    }


}