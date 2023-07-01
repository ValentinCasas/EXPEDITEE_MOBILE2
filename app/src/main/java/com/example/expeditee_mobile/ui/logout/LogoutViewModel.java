package com.example.expeditee_mobile.ui.logout;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.expeditee_mobile.LoginActivity2;
import com.example.expeditee_mobile.MainActivity;
import com.example.expeditee_mobile.R;


public class LogoutViewModel extends AndroidViewModel {

    private Context context;

    public LogoutViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void mostrarDialogoDeConfirmacion(Fragment fragment) {
        Context context = fragment.requireContext();

        // Crear el diálogo de confirmación con el fondo y el color de texto personalizados
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        builder.setTitle("Cierre de sesión");
        builder.setMessage("¿Está seguro que desea cerrar la sesión?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Cierra la sesión y lleva al usuario de vuelta al MainActivity
                Intent intent = new Intent(context, LoginActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                fragment.requireActivity().finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        // Obtener la referencia al diálogo y establecer el fondo y el color de texto
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                // Establecer el color de fondo
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#197A3C")));

                // Obtener los botones del diálogo
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                // Establecer el color de texto para los botones
                positiveButton.setTextColor(Color.WHITE);
                negativeButton.setTextColor(Color.WHITE);
            }
        });

        // Mostrar el diálogo de confirmación
        dialog.show();
    }
}