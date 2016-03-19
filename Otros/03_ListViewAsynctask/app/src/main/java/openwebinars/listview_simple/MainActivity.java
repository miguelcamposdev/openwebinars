package openwebinars.listview_simple;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] personas;
    ListView lista;
    ArrayAdapter<String> adaptadorPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listView);

        personas = new String[] {"María","Ana","Mercedes","Gloria","Rebeca","Olivia"};

        adaptadorPersonas = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,personas);

        lista.setAdapter(adaptadorPersonas);

        lista.setOnItemClickListener(this);

        new TareaGetUsuarios().execute();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Se ha seleccionado: "+personas[position],Toast.LENGTH_SHORT).show();
    }

    private class TareaGetUsuarios extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... params) {
            String urlConsulta = "http://rest.miguelcr.com/friender/users_new";

            JSONArray resultado = Util.getArrayResultado(urlConsulta);
            return resultado;
        }

        @Override
        protected void onPostExecute(JSONArray resultado) {
            super.onPostExecute(resultado);

            if(resultado==null) {
                Toast.makeText(MainActivity.this,"No existen usuarios",Toast.LENGTH_SHORT).show();
            } else {
                personas = new String[resultado.length()];
                try {
                for(int i=0; i<resultado.length(); i++) {

                        JSONObject j = resultado.getJSONObject(i);
                        personas[i] =  j.getString("nickname");
                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adaptadorPersonas = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,personas);

                lista.setAdapter(adaptadorPersonas);
            }
        }
    }


}
