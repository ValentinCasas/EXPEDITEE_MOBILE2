package com.example.expeditee_mobile.ui.chat;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.expeditee_mobile.MainActivity;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.request.ApiClientRetrofit;
import com.example.expeditee_mobile.ui.pendientes.PendientesViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Usuario>> empleadosMutable;
    private MutableLiveData<Usuario> usuarioMutable;

    public ContactosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Usuario>> getEmpleados() {
        if (empleadosMutable == null) {
            empleadosMutable = new MutableLiveData<>();
        }
        return empleadosMutable;
    }

    public LiveData<Usuario> getUsuarioMutable() {
        if (usuarioMutable == null) {
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }


    public void obtenerEmpleados() {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        obtenerPropietario(new PendientesViewModel.OnObtenerPropietarioListener() {
            @Override
            public void onObtenerPropietarioSuccess(int idUsuario) {
                ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
                Call<ArrayList<Usuario>> call = end.obtenerEmpleados(token);

                call.enqueue(new Callback<ArrayList<Usuario>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Usuario> clientesPendientes = response.body();
                            if (clientesPendientes != null) {
                                int totalUsuarios = clientesPendientes.size();
                                AtomicInteger contadorImagenesCargadas = new AtomicInteger(0);

                                for (Usuario usuario : clientesPendientes) {
                                    obtenerImagenUsuario(usuario.getId(), usuario, new PendientesViewModel.OnImagenCargadaListener() {
                                        @Override
                                        public void onImagenCargada() {
                                            int imagenesCargadas = contadorImagenesCargadas.incrementAndGet();
                                            if (imagenesCargadas == totalUsuarios) {
                                                empleadosMutable.setValue(clientesPendientes);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {
                        Toast.makeText(context, "Error al obtener los usuarios pendientes", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onObtenerPropietarioError() {
                Toast.makeText(context, "Error al obtener propietario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerImagenUsuario(int id, Usuario usuario, PendientesViewModel.OnImagenCargadaListener listener) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
        Call<ResponseBody> call = end.obtenerImagenUsuario(token, id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        byte[] imageBytes = response.body().bytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        usuario.setImagenFile(bitmap);
                        listener.onImagenCargada();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error al obtener imagen", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
                Toast.makeText(context, "Error al obtener imagen", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnImagenCargadaListener {
        void onImagenCargada();
    }


    public void obtenerPropietario(final PendientesViewModel.OnObtenerPropietarioListener listener) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        if (token.isEmpty()) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            listener.onObtenerPropietarioError(); // Llamar al callback de error
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
                        listener.onObtenerPropietarioSuccess(usuario.getId()); // Llamar al callback de éxito con el ID del usuario
                    }
                } else {
                    listener.onObtenerPropietarioError(); // Llamar al callback de error
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener usuario", Toast.LENGTH_SHORT).show();
                listener.onObtenerPropietarioError(); // Llamar al callback de error
            }
        });
    }



    public interface OnObtenerPropietarioListener {
        void onObtenerPropietarioSuccess(int idUsuario);
        void onObtenerPropietarioError();
    }
}
