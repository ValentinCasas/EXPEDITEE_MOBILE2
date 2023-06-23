package com.example.expeditee_mobile.ui.cobro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.expeditee_mobile.models.Usuario;

import java.util.ArrayList;

public class CobroViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Usuario>> pendientesMutable;

    public CobroViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Usuario>> getPendientes() {
        if (pendientesMutable == null) {
            pendientesMutable = new MutableLiveData<>();
        }
        return pendientesMutable;
    }


    public void obtenerClientesPendientes() {
        Usuario usuario1 = new Usuario("1", "García", "Madrid", "clave123", "usuario1@mail.com", "Calle Principal 123", 123456789L, "imagen1.jpg", "-33.18839400", "-66.32133100", "Juan", "España", 1, 987654321L);
        Usuario usuario2 = new Usuario("2", "López", "Barcelona", "clave456", "usuario2@mail.com", "Avenida Central 456", 987654321L, "imagen2.jpg", "-33.18839400", "-66.32133100", "María", "España", 2, 123456789L);
        Usuario usuario3 = new Usuario("3", "Smith", "New York", "password123", "user3@mail.com", "Main Street 789", 555555555L, "image3.jpg", "-33.18839400", "-66.32133100", "John", "Estados Unidos", 1, 111111111L);
        Usuario usuario4 = new Usuario("4", "Kim", "Seúl", "secretpass", "user4@mail.com", "Gangnam Road 321", 987654321L, "image4.jpg", "-33.18839400", "-66.32133100", "Ji-Hyun", "Corea del Sur", 2, 222222222L);
        Usuario usuario5 = new Usuario("5", "Müller", "Berlín", "pass1234", "user5@mail.com", "Hauptstrasse 555", 123456789L, "image5.jpg", "-33.18839400", "-66.32133100", "Thomas", "Alemania", 1, 333333333L);

        ArrayList<Usuario> pendientes = new ArrayList<>();
        pendientes.add(usuario1);
        pendientes.add(usuario2);
        pendientes.add(usuario3);
        pendientes.add(usuario4);
        pendientes.add(usuario5);

        pendientesMutable.setValue(pendientes);
    }

}
