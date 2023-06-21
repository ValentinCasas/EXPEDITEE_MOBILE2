package com.example.expeditee_mobile.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiClientRetrofit {

    private static final String PATH = "http://192.168.0.101:5200/api/";
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

        /*
        //login propietario
        @POST("Propietario/loginn")
        Call<String> login(@Body Usuario usuario);

        //perfil del propietario
        @GET("Propietario/data")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        //actualizar perfil
        @POST("Inmueble/actualizar-perfil")
        Call<Propietario> actualizarPerfil(@Header("Authorization") String token, @Body Propietario propietario);

        //mis propiedades
        @GET("Inmueble/propiedades")
        Call<ArrayList<Inmueble>> obtnerPropiedades(@Header("Authorization") String token);

        //actualizar inmueble
        @POST("Inmueble/actualizar-inmueble")
        Call<Inmueble> actualizarInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        //mis propiedades
        @GET("Inmueble/propiedades-alquiladas")
        Call<ArrayList<Inmueble>> obtnerPropiedadesAlquiladas(@Header("Authorization") String token);

        //Dado un inmueble, retorna el inquilino del ultimo contrato activo de ese inmueble
        @POST("Inmueble/inquilino-ultimo-contrato")
        Call<Inquilino> obtnerInquilino(@Header("Authorization") String token, @Body Inmueble inmueble);

        //contratos activos por inmueble
        @POST("Inmueble/contrato-vigente")
        Call<Contrato> contratosPorInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        //pagos del contrato
        @GET("Inmueble/pagos-contrato/{contratoId}")
        Call<ArrayList<Pago>> pagosPorContrato(@Header("Authorization") String token, @Path("contratoId") int contratoId);

        //imagen del propietario
        @GET("Propietario/imagen/{id}")
        Call<ResponseBody> obtenerImagenPropietario(@Header("Authorization") String token, @Path("id") int id);
        */

    }

}


