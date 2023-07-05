package com.example.expeditee_mobile.request;

import com.example.expeditee_mobile.models.Mensaje;
import com.example.expeditee_mobile.models.Pedido;
import com.example.expeditee_mobile.models.Retroalimentacion;
import com.example.expeditee_mobile.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClientRetrofit {

    private static final String PATH = "http://192.168.0.101:5250/api/";
    private static EndPointExpeditee endPointInmobiliaria;

    public static EndPointExpeditee getEndPointInmobiliaria() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endPointInmobiliaria = retrofit.create(EndPointExpeditee.class);

        return endPointInmobiliaria;
    }


    public interface EndPointExpeditee {

        //login usuario - empleado/admin
        @POST("Usuario/login")
        Call<String> login(@Body Usuario usuario);

        //perfil del usuario
        @GET("Usuario/data")
        Call<Usuario> obtenerPerfil(@Header("Authorization") String token);

        //imagen del propietario
        @GET("Usuario/imagen/{id}")
        Call<ResponseBody> obtenerImagenUsuario(@Header("Authorization") String token, @Path("id") int id);

        @GET("Usuario/misPendientes/{id}")
        Call<ArrayList<Usuario>> obtenerMisPendientes(@Header("Authorization") String token, @Path("id") int id);

        @GET("Usuario/empleados")
        Call<ArrayList<Usuario>> obtenerEmpleados(@Header("Authorization") String token);

        @GET("Pedido/obtenerPedidoPorId/{id}")
        Call<Pedido> obtenerPedidoPorId(@Header("Authorization") String token, @Path("id") int id);


        @GET("Mensaje/enviar/{idEmisor}/{idReceptor}/{mensaje}")
        Call<Mensaje> enviarMensaje(@Header("Authorization") String token, @Path("idEmisor") int idEmisor, @Path("idReceptor") int idReceptor, @Path("mensaje") String mensaje);

        @GET("Mensaje/mensajes/{idEmisor}/{idReceptor}")
        Call<ArrayList<Mensaje>> obtenerMensajes(@Header("Authorization") String token, @Path("idEmisor") int idEmisor, @Path("idReceptor") int idReceptor);

        @GET("Usuario/generarImgComprobante/{idPedido}/{montoTotal}/{nombreEmpleado}/{nombreCliente}/{mail}")
        Call<ResponseBody> generarComprobante(@Header("Authorization") String token, @Path("idPedido") int idPedido, @Path("montoTotal") double montoTotal, @Path("nombreEmpleado") String nombreEmpleado, @Path("nombreCliente") String nombreCliente, @Path("mail") String mail);

        @GET("Usuario/usuarioPorId/{id}")
        Call<Usuario> getUsuario(@Header("Authorization") String token, @Path("id") int id);

        @GET("Pedido/actualizarEstadoPedido/{id}")
        Call<Void> actualizarEstadoPedido(@Header("Authorization") String token, @Path("id") int id);

        @GET("Retroalimentacion/retroalimentaciones")
        Call<ArrayList<Retroalimentacion>> obtenerRetroalimentaciones(@Header("Authorization") String token);


    }

}


