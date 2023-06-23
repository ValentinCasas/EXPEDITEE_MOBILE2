package com.example.expeditee_mobile.ui.chat;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.expeditee_mobile.models.Usuario;

import java.util.ArrayList;

public class ContactosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Usuario>> empleadosMutable;

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


    public void obtenerEmpleados() {
        Usuario usuario1 = new Usuario("1", "García", "Madrid", "clave123", "usuario1@mail.com", "Calle Principal 123", 123456789L, "imagen1.jpg", "-33.18839400", "-66.32133100", "Juan", "España", 1, 987654321L);
        Usuario usuario2 = new Usuario("2", "López", "Barcelona", "clave456", "usuario2@mail.com", "Avenida Central 456", 987654321L, "imagen2.jpg", "-33.18839400", "-66.32133100", "María", "España", 2, 123456789L);
        Usuario usuario3 = new Usuario("3", "Smith", "New York", "password123", "user3@mail.com", "Main Street 789", 555555555L, "image3.jpg", "-33.18839400", "-66.32133100", "John", "Estados Unidos", 1, 111111111L);
        Usuario usuario4 = new Usuario("4", "Kim", "Seúl", "secretpass", "user4@mail.com", "Gangnam Road 321", 987654321L, "image4.jpg", "-33.18839400", "-66.32133100", "Ji-Hyun", "Corea del Sur", 2, 222222222L);
        Usuario usuario6 = new Usuario("5", "Müller", "Berlín", "pass1234", "user5@mail.com", "Hauptstrasse 555", 123456789L, "image5.jpg", "-33.18839400", "-66.32133100", "Thomas", "Alemania", 1, 333333333L);
        Usuario usuario7 = new Usuario("1", "García", "Madrid", "clave123", "usuario1@mail.com", "Calle Principal 123", 123456789L, "imagen1.jpg", "-33.18839400", "-66.32133100", "Juan", "España", 1, 987654321L);
        Usuario usuario8 = new Usuario("2", "López", "Barcelona", "clave456", "usuario2@mail.com", "Avenida Central 456", 987654321L, "imagen2.jpg", "-33.18839400", "-66.32133100", "María", "España", 2, 123456789L);
        Usuario usuario9 = new Usuario("3", "Smith", "New York", "password123", "user3@mail.com", "Main Street 789", 555555555L, "image3.jpg", "-33.18839400", "-66.32133100", "John", "Estados Unidos", 1, 111111111L);
        Usuario usuario10 = new Usuario("4", "Kim", "Seúl", "secretpass", "user4@mail.com", "Gangnam Road 321", 987654321L, "image4.jpg", "-33.18839400", "-66.32133100", "Ji-Hyun", "Corea del Sur", 2, 222222222L);
        Usuario usuario11 = new Usuario("5", "Müller", "Berlín", "pass1234", "user5@mail.com", "Hauptstrasse 555", 123456789L, "image5.jpg", "-33.18839400", "-66.32133100", "Thomas", "Alemania", 1, 333333333L);


        ArrayList<Usuario> empleados = new ArrayList<>();
        empleados.add(usuario1);
        empleados.add(usuario2);
        empleados.add(usuario3);
        empleados.add(usuario4);
        empleados.add(usuario6);
        empleados.add(usuario7);
        empleados.add(usuario8);
        empleados.add(usuario9);
        empleados.add(usuario10);
        empleados.add(usuario11);

        empleadosMutable.setValue(empleados);
    }

}
