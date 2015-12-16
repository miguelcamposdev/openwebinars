package openwebinars.navigationdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import openwebinars.navigationdrawer.R;
import openwebinars.navigationdrawer.models.User;

/**
 * Created by miguelcampos on 26/11/15.
 */
public class AdaptadorPersona extends ArrayAdapter<User> {
    Context contexto;
    List<User> personas;
    int layoutPersona;

    public AdaptadorPersona(Context context, int resource, List<User> objects) {
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

        User personaActual = personas.get(position);

        // Rescatar los elementos del Layout (R.layout.list_item_persona)
        ImageView icono = (ImageView) layoutPersonaAInyectar.findViewById(R.id.imageViewTipo);
        TextView nombre = (TextView) layoutPersonaAInyectar.findViewById(R.id.textViewNombrePersona);
        TextView edad = (TextView) layoutPersonaAInyectar.findViewById(R.id.textViewEdad);

        icono.setImageResource(R.drawable.ic_like);

        nombre.setText(personaActual.getNickname());
        edad.setText("Sexo: "+String.valueOf(personaActual.getSexo()));

        return layoutPersonaAInyectar;
    }
}
