package com.salesianostriana.dam.di.apptalkme.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.salesianostriana.dam.di.apptalkme.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class EnviarMensajeActivity extends AppCompatActivity {
    EditText et_mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mensaje);
        Intent intent = getIntent();
        getSupportActionBar().setTitle(intent.getStringExtra("contacto"));
        et_mensaje = (EditText) findViewById(R.id.enviar_mensaje_et_enviar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_enviar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                new EnviarMensajeTask().execute(et_mensaje.getText().toString());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //http://miguelcr.hol.es/talkme/send?regId=REGISTRATION_ID&nickname=NICK_NAME&mensaje=MENSAJE

    private class EnviarMensajeTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            URL url = null;
            HttpURLConnection urlConnection = null;
            JSONArray response = null;
            if(params!=null){
                try {

                    SharedPreferences prefs = getSharedPreferences("NuevasPreferencias", Context.MODE_PRIVATE);
                    String clave = prefs.getString("clave", "");
                    String direccion = "http://miguelcr.hol.es/talkme/send?regId=" + clave + "&nickname=" + getSupportActionBar().getTitle() + "&mensaje=" + URLEncoder.encode(params[0], "UTF-8");
                    Log.i("registro", direccion);
                    url = new URL(direccion);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Snackbar.make(EnviarMensajeActivity.this.getCurrentFocus(),"Enviado el mensaje con éxito", Snackbar.LENGTH_SHORT).setAction("action", null).show();
            startActivity(new Intent(EnviarMensajeActivity.this, TabsActivity.class));
        }
    }

}
