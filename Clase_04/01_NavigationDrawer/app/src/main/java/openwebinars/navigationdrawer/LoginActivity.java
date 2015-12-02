package openwebinars.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    EditText nick;
    Button btn;

    //SENDER_ID servidor api Friender: 813103604059

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = this.getSharedPreferences("NuevasPreferencias", Context.MODE_PRIVATE);

        // Si el usuario ya está registrado obvio el formulario de Login
        if(prefs.getBoolean("recordar",false)) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        } else {
            setContentView(R.layout.activity_login);

            nick = (EditText) findViewById(R.id.editTextNickname);

            btn = (Button) findViewById(R.id.buttonEntrar);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GcmRegistrationAsyncTask(LoginActivity.this).execute(nick.getText().toString());
                }
            });

        }

    }

    public class GcmRegistrationAsyncTask extends AsyncTask<String, Void, String> {
        private GoogleCloudMessaging gcm;

        private String registrationID = "";
        JSONArray response = new JSONArray();
        String nick;

        private static final String SENDER_ID = "813103604059";

        private Context context;
        String parametroUno;

        public GcmRegistrationAsyncTask(Context context) {
            this.context = context;
            //...
        }


        @Override
        protected String doInBackground(String... params) {

            String msg = "";
            String nick = params[0];
            try {
                if (gcm == null){
                    gcm = GoogleCloudMessaging.getInstance(context);
                }

                // PROCESO 1: Registrar el dispositvio en Google Cloud Messaging
                // Como resultado de este proceso, recibo de GCM un identificador único
                // que almaceno en la variable registrationId
                registrationID = gcm.register(SENDER_ID);

                // PROCESO 2: enviar al servidor de mi API Friender el registrationId
                // Además le mando el nombre de usuario (nickname) con el que el usuario
                // se va a identificar en la aplicación
                registroEnServidor();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return msg;

        }

        public void registroEnServidor() {
            URL url = null;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL("http://rest.miguelcr.com/friender/register?regId=" + registrationID + "&nickname=" + nick + "&password=1234");
                Log.i("registro", url.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                urlConnection = (HttpURLConnection) url.openConnection();

                String responseString = readStream(urlConnection.getInputStream());
                try {
                    response = new JSONArray(responseString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }

        @Override
        protected void onPostExecute(String msg) {
            SharedPreferences prefs = context.getSharedPreferences("NuevasPreferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            // boolean
            editor.putBoolean("recordar", true);
            editor.putString("nick", nick);
            editor.commit();

            Toast.makeText(LoginActivity.this, "Registro concluido", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
    }



}
