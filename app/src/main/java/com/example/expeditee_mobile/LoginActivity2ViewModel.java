package com.example.expeditee_mobile;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LoginActivity2ViewModel extends AndroidViewModel {

    private Context context;

    public LoginActivity2ViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void confirmaLogin(String email, String password) {
        //validar datos
        Intent intent = new Intent(context, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

}
