package com.example.expeditee_mobile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expeditee_mobile.models.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expeditee_mobile.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;
    private MenuActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        mv = new ViewModelProvider(this).get(MenuActivityViewModel.class);
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenu.toolbar);

        View headerView = binding.navView.getHeaderView(0);
        ImageView imageView = headerView.findViewById(R.id.imageView);
        TextView tvNombre = headerView.findViewById(R.id.textViewName);
        TextView tvEmail = headerView.findViewById(R.id.textViewEmail);

        mv.obtenerPropietario();

        mv.getImagenMutable().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap imageBytes) {
                if (imageBytes != null) {

                    imageView.setImageBitmap(imageBytes);
                }
            }
        });

        mv.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                tvNombre.setText(usuario.getNombre() + " " + usuario.getApellido());
                tvEmail.setText(usuario.getMail());
            }
        });


        NavigationView navigation = findViewById(R.id.nav_view);
        Menu menu = navigation.getMenu();
        MenuItem menuItem1 = menu.findItem(R.id.nav_inicio);
        MenuItem menuItem2 = menu.findItem(R.id.nav_pendientes);
        MenuItem menuItem3 = menu.findItem(R.id.nav_cobro);
        MenuItem menuItem4 = menu.findItem(R.id.nav_contactos);
        MenuItem menuItem5 = menu.findItem(R.id.nav_salir);
        menuItem1.setIcon(R.drawable.circle_green);
        menuItem2.setIcon(R.drawable.circle_green);
        menuItem3.setIcon(R.drawable.circle_green);
        menuItem4.setIcon(R.drawable.circle_green);
        menuItem5.setIcon(R.drawable.circle_red);



        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_inicio,
                    R.id.nav_pendientes,
                    R.id.nav_cobro,
                    R.id.nav_contactos,
                R.id.nav_salir)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}