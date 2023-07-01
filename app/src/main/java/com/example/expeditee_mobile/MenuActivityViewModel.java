package com.example.expeditee_mobile;

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


import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.request.ApiClientRetrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;;

public class MenuActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<Bitmap> imagenMutable;

    public MenuActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Bitmap> getImagenMutable() {
        if(imagenMutable == null){
            imagenMutable = new MutableLiveData<>();
        }
        return imagenMutable;
    }

    public LiveData<Usuario> getUsuarioMutable() {
        if(usuarioMutable == null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }

    public void obtenerPropietario() {
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
                        usuarioMutable.setValue(response.body());

                        // Obtener la imagen del propietario
                        obtenerImagenUsuario(response.body().getId());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerImagenUsuario(int id) {
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

                        // Convertir los bytes de la imagen a un objeto Bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                        // Guardar el objeto Bitmap en un MutableLiveData
                        imagenMutable.setValue(bitmap);

                        // Utilizar el MutableLiveData como sea necesario
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

}