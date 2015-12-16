package openwebinars.navigationdrawer.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import openwebinars.navigationdrawer.R;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String nick = extras.getString("nick");
        String sexo = extras.getString("sexo");
        String id = extras.getString("id");

        // Establecemos el titulo del toolbar con el nick del usuario que estamos
        // mostrando
        getSupportActionBar().setTitle(nick);

        // Imagen
        ImageView imagenPersona = (ImageView) findViewById(R.id.imagenSitio);
        Picasso.with(this).load("http://rest.miguelcr.com/images/friender/" + id + ".png").into(imagenPersona);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
