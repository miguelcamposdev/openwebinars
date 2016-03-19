package com.miguelcr.asynctaskexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText nick, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nick = (EditText) findViewById(R.id.editTextNick);
        pass = (EditText) findViewById(R.id.editTextPass);
    }

    public void doLogin(View view) {
        new MiTareaLogin().execute("http://rest.miguelcr.com/friender/login",nick.getText().toString(),pass.getText().toString());
    }

    private class MiTareaLogin extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String user = params[1];
            String pass = params[2];

            String urlConsulta = url+"?nickname="+user+"&password="+pass;

            String resultadoConsulta = Util.getResultadoUrl(urlConsulta);


            return resultadoConsulta;
        }


        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            if(resultado==null) {
                Toast.makeText(MainActivity.this, "Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private class GetUsersTask extends AsyncTask<Params, Progress,JSONArray> {

        @Override
        protected JSONArray doInBackground(Params... params) {
            return null;

            Util.
        }

        @Override
        protected void onPostExecute(JSONArray resultado) {
            super.onPostExecute(result);

            if(resultado==null) {

            } else {
                try {
                    for (int i = 0; i< resultado.length(); i++){
                        JSONObject j = resultado.getJSONObject(i);

                        ranking.add(new DatoUser(String.valueOf(i+1),j.getString("nickname"),j.getString("points")));
                        Log.i("ErrorJson", j.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adaptador = new DatosAdapter(getBaseContext(),ranking);
                lista.setAdapter(adaptador);
            }
        }
    }
}


