package openwebinars.holamundo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {
    TextView nombreAMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        // Enlazo el elemento del layout cuyo id es R.id.textViewNombreRecibido
        // con la variable nombreAMostrar
        nombreAMostrar = (TextView)findViewById(R.id.textViewNombreRecibido);

        Bundle extras = getIntent().getExtras();
        String nombreUsuario = extras.getString("nombre");
        nombreAMostrar.setText("Nombre recibido: "+nombreUsuario);

    }
}
