package com.example.expeditee_mobile.ui.inicio;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.expeditee_mobile.models.Mensaje;
import com.example.expeditee_mobile.models.Retroalimentacion;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.request.ApiClientRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Retroalimentacion>> retroalimentacionMutable;


    public InicioViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Retroalimentacion>> getRetroalimentacionMutable() {
        if (retroalimentacionMutable == null) {
            retroalimentacionMutable = new MutableLiveData<>();
        }
        return retroalimentacionMutable;
    }

    public void obtenerRetroalimentaciones() {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
        Call<ArrayList<Retroalimentacion>> call = end.obtenerRetroalimentaciones(token);

        call.enqueue(new Callback<ArrayList<Retroalimentacion>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Retroalimentacion>> call, @NonNull Response<ArrayList<Retroalimentacion>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Retroalimentacion> retroalimentaciones = response.body();
                    if (retroalimentaciones != null) {

                        retroalimentacionMutable.setValue(retroalimentaciones);
                    }
                } else {
                    String errorMessage = "Error: " + response.code() + " - " + response.message();
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Retroalimentacion>> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener los retroalimentaciones" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}