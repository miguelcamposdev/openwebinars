package openwebinars.listview_personalizado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ItemPersona> personas;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listView);

        personas = new ArrayList<>();
        personas.add(new ItemPersona("María",25,2));
        personas.add(new ItemPersona("Rebeca",28,2));
        personas.add(new ItemPersona("Ana",25,1));
        personas.add(new ItemPersona("Mercedes",26,1));

        AdaptadorPersona adaptadorPersonas = new AdaptadorPersona(this,R.layout.list_item_persona,personas);

        lista.setAdapter(adaptadorPersonas);

    }
}
