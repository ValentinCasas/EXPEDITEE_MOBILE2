package com.example.expeditee_mobile.ui.pendientes;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expeditee_mobile.R;
import com.example.expeditee_mobile.databinding.FragmentUbicacionBinding;
import com.example.expeditee_mobile.models.Pedido;
import com.example.expeditee_mobile.models.Usuario;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionFragment extends Fragment implements OnMapReadyCallback {

    private UbicacionViewModel mv;
    private FragmentUbicacionBinding binding;
    private Usuario cliente;
    private FusedLocationProviderClient fusedLocationClient;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;

    public static UbicacionFragment newInstance() {
        return new UbicacionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUbicacionBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(UbicacionViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        cliente = (Usuario) bundle.getSerializable("cliente");

        binding.tvNombre.setText(cliente.getNombre());
        binding.tvDireccion.setText(cliente.getDireccion());
        binding.tvTelefono.setText(String.valueOf(cliente.getTelefono()));

        mv.getImagenMutable().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap imageBytes) {
                if (imageBytes != null) {

                    binding.ivCliente.setImageBitmap(imageBytes);
                }
            }
        });

        mv.obtenerImagenUsuario(cliente.getId());
        binding.btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cliente", cliente);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_cobroPendiente, bundle);
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }



        mv.getPedidoMutable().observe(getViewLifecycleOwner(), new Observer<Pedido>() {
            @Override
            public void onChanged(Pedido pedido) {

                mv.getEmpleadoMutable().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
                    @Override
                    public void onChanged(Usuario empleado) {

                        binding.btnEntregar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mv.actualizarEstadoPedido(pedido.getId());
                                binding.btnEntregar.setText("ENTREGADO");
                                binding.btnEntregar.setEnabled(false);
                            }
                        });

                    }
                });

                mv.getUsuario(pedido.getIdEmpleado());

            }

        });

        mv.obtenerPedido(cliente.getIdPedido());

        return root;
    }


    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mv.getMarcadores().observe(getViewLifecycleOwner(), markerOptionsList -> {
            mMap.clear(); // Limpiamos todos los marcadores anteriores del mapa

            for (MarkerOptions markerOptions : markerOptionsList) {
                mMap.addMarker(markerOptions);
            }
        });

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

            mv.getUbicacionActual().observe(getViewLifecycleOwner(), location -> {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            });

            mv.actualizarUbicacion(cliente.getLatitud(),cliente.getLongitud());
        }
    }



}