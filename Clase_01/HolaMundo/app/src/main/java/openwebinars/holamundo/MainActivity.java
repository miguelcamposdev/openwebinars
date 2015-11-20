package openwebinars.holamundo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextnombre, editTextTelefono;
    Button btnComoMeLlamo, btnEvento, btnLanzarActivity;
    Button btnLlamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar la interfaz de usuario en el dispositivo.
        setContentView(R.layout.activity_main);

        editTextnombre = (EditText)findViewById(R.id.editText);
        editTextTelefono = (EditText)findViewById(R.id.editTextTelefono);
        btnComoMeLlamo = (Button)findViewById(R.id.button);
        btnEvento = (Button)findViewById(R.id.buttonEvento);
        btnLanzarActivity = (Button)findViewById(R.id.buttonLanzarActivity);
        btnLlamar = (Button)findViewById(R.id.buttonLlamarTelefono);

        btnEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextnombre.getText().toString();
                Toast.makeText(MainActivity.this, "Te llamas: "+nombre, Toast.LENGTH_SHORT).show();
            }
        });

        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para lanzar un nuevo Activity.
                Intent i = new Intent(MainActivity.this,SecondaryActivity.class);
                i.putExtra("nombre",editTextnombre.getText().toString());
                startActivity(i);
            }
        });

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para lanzar un nuevo Activity.
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+editTextTelefono.getText().toString()));
                startActivity(i);
            }
        });


    }

    public void mostrarNombre(View v) {
        String nombre = editTextnombre.getText().toString();
        Toast.makeText(MainActivity.this, "Te llamas: "+nombre, Toast.LENGTH_SHORT).show();
    }
}
