package com.salesianostriana.maps;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.Map;

/**
 * Created by miguelcampos on 8/12/15.
 */
public class PopupAdapter implements GoogleMap.InfoWindowAdapter{
    private View popup=null;
    private LayoutInflater inflater=null;
    private Map<Marker, Ciudad> ciudades;

    public PopupAdapter(LayoutInflater inflater, Map<Marker,Ciudad> ciudades) {

        this.inflater = inflater;
        this.ciudades = ciudades;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (popup == null) {
            popup=inflater.inflate(R.layout.popup_marker, null);
        }

        // Obtengo la información de la ciudad actual, a partir del Marker
        Ciudad ciudadActual = ciudades.get(marker);

        // Titulo
        TextView tv=(TextView)popup.findViewById(R.id.tituloPopup);
        tv.setText(ciudadActual.getNombre());

        // Descripcion
        tv=(TextView)popup.findViewById(R.id.descripcionPopup);
        tv.setText(marker.getSnippet());

        // Icono
        ImageView icono = (ImageView)popup.findViewById(R.id.imageView);
        // icono.setImageResource(R.drawable.ic_launcher);

        return(popup);
    }
}
