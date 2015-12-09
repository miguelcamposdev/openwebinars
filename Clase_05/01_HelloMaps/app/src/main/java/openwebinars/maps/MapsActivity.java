package openwebinars.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private LatLng marcadorNYC, marcadorMadrid;
    Marker marker = null;
    LatLng ultimaPosicion = null;

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

        // El mapa va a permitir eventos sobre los markers
        mMap.setOnMarkerDragListener(this);
        // El mapa va a permitir eventos click sobre el mapa
        mMap.setOnMapClickListener(this);

        // Marker Madrid
        marcadorMadrid = new LatLng(40.423383,-3.712165);

        // Marker NYC
        marcadorNYC = new LatLng(40.712784,-74.005941);

        mMap.addMarker(new MarkerOptions()
                .position(marcadorMadrid)
                .title("Curso Android Tinder")
                .snippet("Openwebinars.net"));

        mMap.addMarker(new MarkerOptions()
                .position(marcadorNYC)
                .title("NYC")
                .snippet("Openwebinars.net"));

        mMap.animateCamera(CameraUpdateFactory.newLatLng(marcadorMadrid));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.goto_marker:
                Toast.makeText(MapsActivity.this,"Vamos a NYC",Toast.LENGTH_LONG).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(marcadorNYC));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onMapClick(LatLng latLng) {
        if(marker==null) {
            marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .draggable(true)
                    .title("Nuevo Marcador")
                    .snippet(String.valueOf(latLng.latitude) + "," + String.valueOf(latLng.longitude)));

            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_flag));
            ultimaPosicion = latLng;
        } else {
            marker.setPosition(latLng);

            PolylineOptions rectOptions = new PolylineOptions()
                    .add(ultimaPosicion)
                    .add(latLng)
                    .color(Color.BLUE)
                    .width(15); // Closes the polyline.

            Polyline polyline = mMap.addPolyline(rectOptions);

            ultimaPosicion = latLng;
        }


    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Log.i("MAPA","MARKER: "+marker.getPosition().latitude+","+marker.getPosition().longitude);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        marker.setTitle("Me han soltado");
        marker.setSnippet(marker.getPosition().latitude+","+marker.getPosition().longitude);
    }
}
