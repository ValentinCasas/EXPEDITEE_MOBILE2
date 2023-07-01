package com.example.expeditee_mobile.ui.cobro;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.expeditee_mobile.models.Pedido;
import com.example.expeditee_mobile.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MercadoPagoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Pedido> pedidoMutable;


    public MercadoPagoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Pedido> getPedidoMutable() {
        if (pedidoMutable == null) {
            pedidoMutable = new MutableLiveData<>();
        }
        return pedidoMutable;
    }

    public void obtenerPedido(int id) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
        Call<Pedido> call = end.obtenerPedidoPorId(token, id);

        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(@NonNull Call<Pedido> call, @NonNull Response<Pedido> response) {
                if (response.isSuccessful()) {
                    Pedido pedido = response.body();
                    if (pedido != null) {

                        Log.d("Pedido", pedido.getMontoTotal() + "");
                        pedidoMutable.setValue(pedido);
                    }
                } else {
                    String errorMessage = "Error al obtener el pedido: " + response.code() + " - " + response.message();
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pedido> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener el pedido 2" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}