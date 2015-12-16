package com.salesianostriana.maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Map<Marker, Ciudad> allMarkersMap = new HashMap<Marker, Ciudad>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // ****** for(...) { ********

        // Add a marker in Sydney and move the camera
        LatLng triana = new LatLng(37.380346,-6.007744);
        Marker marcadorTriana = mMap.addMarker(new MarkerOptions()
                .position(triana));

        marcadorTriana.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marker));
        marcadorTriana.setTitle("Openwebinars");
        marcadorTriana.setSnippet("Curso Android");
        marcadorTriana.showInfoWindow();

        // Lleno el map de Marcadores (markers)
        allMarkersMap.put(marcadorTriana, new Ciudad(1,"Madrid"));

        // *********** } fin bucle for **************

        mMap.moveCamera(CameraUpdateFactory.newLatLng(triana));

        mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater(), allMarkersMap));
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Ciudad ciudad = allMarkersMap.get(marker);

        Toast.makeText(this, ciudad.getNombre(), Toast.LENGTH_LONG).show();
        //Intent i...
        // i.putExtra("idCiudad",ciudad.getId());
        // startActivity(i);
    }
}