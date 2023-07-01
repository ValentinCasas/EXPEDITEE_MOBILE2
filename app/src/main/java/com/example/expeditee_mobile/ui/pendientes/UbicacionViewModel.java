package com.example.expeditee_mobile.ui.pendientes;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.expeditee_mobile.MainActivity;
import com.example.expeditee_mobile.models.Pedido;
import com.example.expeditee_mobile.models.Usuario;
import com.example.expeditee_mobile.request.ApiClientRetrofit;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbicacionViewModel extends AndroidViewModel {

    private FusedLocationProviderClient fusedLocationClient;
    private MutableLiveData<Bitmap> imagenMutable;
    private MutableLiveData<Location> ubicacionActual = new MutableLiveData<>();
    private MutableLiveData<List<MarkerOptions>> marcadores = new MutableLiveData<>();
    private MutableLiveData<Pedido> pedidoMutable;
    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<Usuario> empleadoMutable;
    private Context context;

    public UbicacionViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application.getApplicationContext());
    }


    public LiveData<Bitmap> getImagenMutable() {
        if(imagenMutable == null){
            imagenMutable = new MutableLiveData<>();
        }
        return imagenMutable;
    }

    public LiveData<Pedido> getPedidoMutable() {
        if (pedidoMutable == null) {
            pedidoMutable = new MutableLiveData<>();
        }
        return pedidoMutable;
    }

    public LiveData<Usuario> getUsuarioMutable() {
        if (usuarioMutable == null) {
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }

    public LiveData<Usuario> getEmpleadoMutable() {
        if (empleadoMutable == null) {
            empleadoMutable = new MutableLiveData<>();
        }
        return empleadoMutable;
    }
    public LiveData<Location> getUbicacionActual() {
        return ubicacionActual;
    }
    public LiveData<List<MarkerOptions>> getMarcadores() {
        return marcadores;
    }

    public void actualizarUbicacion(String latitud, String longitud) {
        if (ContextCompat.checkSelfPermission(getApplication().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    ubicacionActual.setValue(location);
                    actualizarMarcadores(location, latitud, longitud);
                }
            });
        }
    }
    private void actualizarMarcadores(Location location, String latitud, String longitud) {
        List<MarkerOptions> listaMarcadores = new ArrayList<>();

        double latitudDouble = Double.parseDouble(latitud);
        double longitudDouble = Double.parseDouble(longitud);

        LatLng mercado1 = new LatLng(latitudDouble, longitudDouble);
        listaMarcadores.add(new MarkerOptions().position(mercado1).title("A entregar"));

        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            listaMarcadores.add(new MarkerOptions().position(latLng).title("Mi ubicación"));
        }

        marcadores.setValue(listaMarcadores);
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

    public void getUsuario(int id) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        if (token.isEmpty()) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            return;
        }

        ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
        Call<Usuario> call = end.getUsuario(token, id);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        empleadoMutable.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void actualizarEstadoPedido(int idPedido) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        ApiClientRetrofit.EndPointExpeditee end = ApiClientRetrofit.getEndPointInmobiliaria();
        Call<Void> call = end.actualizarEstadoPedido(token, idPedido);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    // La solicitud se completó exitosamente sin devolver ningún dato adicional
                } else {
                    String errorMessage = "Error: " + response.code() + " - " + response.message();
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al actualizar pedido: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}