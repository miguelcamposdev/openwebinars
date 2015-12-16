package openwebinars.navigationdrawer.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import openwebinars.navigationdrawer.fragments.CardsFragment;
import openwebinars.navigationdrawer.fragments.MatchFragment;
import openwebinars.navigationdrawer.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FrameLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Contenedor de la página (Fragments)
        contenedor = (FrameLayout) findViewById(R.id.contenedor);

        // Barrar superior (antigua ActionBar)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Botón flotante situado sobre la página, por defecto abajo/derecha
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // El componente de la interfaz que gestion la relación entre el NavigationView
        // y el Toolbar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // El componente que define el Menú Lateral
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View viewHeader = navigationView.getHeaderView(0);
        ImageView icono = (ImageView)viewHeader.findViewById(R.id.imageViewIcono);
        icono.setImageResource(R.mipmap.ic_launcher);

        TextView textPrincipal = (TextView) viewHeader.findViewById(R.id.tituloPrincipal);
        TextView textSecundario = (TextView) viewHeader.findViewById(R.id.tituloSecundario);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        String nick = prefs.getString("nick", "");

        textPrincipal.setText("Bienvenido a Friender");
        textSecundario.setText(nick);

        // Cargo el Fragment inicial
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.contenedor, new CardsFragment());
        tx.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment f = null;
        String mensaje = "";
        boolean lanzarFragment = false;

        if (id == R.id.nav_fotos) {
            // Handle the camera action
            f = new CardsFragment();
            lanzarFragment = true;
        } else if (id == R.id.nav_like) {
            f = new MatchFragment();
            lanzarFragment = true;
        } else if (id == R.id.nav_mapa) {
            // Nuevo Activity con Google Maps
            Intent i = new Intent(this, MapsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_configuracion) {
            // Nuevo Activity con configuración
            mensaje = "Configuración";
        } else if (id == R.id.nav_exit) {
            // Salir de la aplicación
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = prefs.edit();
            // boolean
            editor.putBoolean("recordar", false);
            editor.putString("nick", null);
            editor.commit();
            this.finish();

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        if(lanzarFragment) {
            getSupportFragmentManager().beginTransaction()
                     .replace(R.id.contenedor, f).commit();
        } else {
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
