package openwebinars.navigationdrawer.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import openwebinars.navigationdrawer.R;
import openwebinars.navigationdrawer.models.User;
import openwebinars.navigationdrawer.utils.Util;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<User> listaUsuarios;
    private static final String BASE_URL = "http://rest.miguelcr.com/friender/users?regId=";

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


        new GetUsers().execute(Util.obtieneId(this));
}

    private class GetUsers extends AsyncTask<String, Void, List<User>> {
        protected List<User> doInBackground(String... params) {
            String url = BASE_URL+params[0];
            List<User> result= new ArrayList<>();
            Log.i("ErrorUrl", url);

            try {

                String jsonText= Util.getStringContentFromUrl(url);

                JSONArray json = new JSONArray(jsonText);
                Log.i("ErrorJson",jsonText);

                for (int i = 0; i< json.length(); i++){
                    JSONObject j = json.getJSONObject(i);
                    result.add(new User(j.getString("nickname"),j.getString("latlon"),j.getString("id"),j.getString("sexo")));
                    Log.i("ErrorJson",j.getString("nickname")+" --------- "+j.getString("sexo"));
                }



            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        protected void onPostExecute(List<User> listanom) {
            listaUsuarios = listanom;
            Marker ultimoMarker = null;
            for(int i=0; i<listaUsuarios.size(); i++) {
                String[] parts = listaUsuarios.get(i).getLatlon().split(",");
                double lat = Double.parseDouble(parts[0]); // lat
                double lon = Double.parseDouble(parts[1]); // lon

                ultimoMarker = mMap.addMarker(new MarkerOptions()
                        .title(listaUsuarios.get(i).getNickname())
                                .snippet(listaUsuarios.get(i).getSexo())
                        .position(new LatLng(lat, lon))
                );
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ultimoMarker.getPosition(),7));
        }


    }
}
