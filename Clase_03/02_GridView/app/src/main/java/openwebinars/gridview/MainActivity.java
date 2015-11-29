package openwebinars.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] personas;
    GridView cuadricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuadricula = (GridView) findViewById(R.id.gridView);

        personas = new String[] {"María","Ana","Mercedes","Gloria","Rebeca","Olivia"};

        ArrayAdapter<String> adaptadorPersonas = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,personas);

        cuadricula.setAdapter(adaptadorPersonas);

        cuadricula.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Seleccionado: "+personas[position], Toast.LENGTH_SHORT).show();
                view.animate().setDuration(1000).rotationX(360);
            }
        });
    }
}
