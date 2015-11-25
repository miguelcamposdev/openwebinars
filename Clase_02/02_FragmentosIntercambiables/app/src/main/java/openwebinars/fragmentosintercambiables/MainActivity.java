package openwebinars.fragmentosintercambiables;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    FrameLayout contenedor;
    ListadoUsuariosFragment listado;
    CuadriculaUsuariosFragment cuadricula;
    boolean cargarCuadricula = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenedor = (FrameLayout) findViewById(R.id.contenedorFragmentos);

        listado = new ListadoUsuariosFragment();
        cuadricula = new CuadriculaUsuariosFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contenedorFragmentos, listado).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opciones_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cambiarVista:
                // Acción cambiar vista
                if(cargarCuadricula) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contenedorFragmentos, cuadricula).commit();
                    cargarCuadricula = false;
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contenedorFragmentos, listado).commit();
                    cargarCuadricula = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
