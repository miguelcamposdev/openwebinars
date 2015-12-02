package openwebinars.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by miguelcampos on 26/11/15.
 */
public class AdaptadorPersona extends ArrayAdapter<ItemPersona> {
    Context contexto;
    ArrayList<ItemPersona> personas;
    int layoutPersona;


    public AdaptadorPersona(Context context, int resource, ArrayList<ItemPersona> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.layoutPersona = resource;
        this.personas = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutPersonaAInyectar = inflater.inflate(layoutPersona, parent, false);

        ItemPersona personaActual = personas.get(position);

        // Rescatar los elementos del Layout (R.layout.list_item_persona)
        ImageView icono = (ImageView) layoutPersonaAInyectar.findViewById(R.id.imageViewTipo);
        TextView nombre = (TextView) layoutPersonaAInyectar.findViewById(R.id.textViewNombrePersona);
        TextView edad = (TextView) layoutPersonaAInyectar.findViewById(R.id.textViewEdad);

        if(personaActual.getTipoCoincidencia()==1) {
            // Like
            icono.setImageResource(R.drawable.ic_like);
        } else {
            //Superlike
            icono.setImageResource(R.drawable.ic_superlike);
        }

        nombre.setText(personaActual.getNombre());
        edad.setText(String.valueOf(personaActual.getEdad())+" años");

        return layoutPersonaAInyectar;
    }
}
