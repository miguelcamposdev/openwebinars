package com.miguelcr.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nick, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nick = (EditText) findViewById(R.id.editTextNick);
        pass = (EditText) findViewById(R.id.editTextPass);
    }

    public void doLogin(View view) {
        String nickname = nick.getText().toString();
        String password = pass.getText().toString();

        new TareaLogin().execute(nickname,password);
    }

    private class TareaLogin extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String nick = params[0];
            String pass = params[1];

            String urlConsulta = "http://rest.miguelcr.com/friender/login?nickname="+nick+"&password="+pass;

            String respuesta = Util.getResultadoUrl(urlConsulta);

            return respuesta;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            if(resultado==null) {
                Toast.makeText(MainActivity.this,"Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,"Login OK",Toast.LENGTH_SHORT).show();
                // Intent i = new Intent(...);
                // startActivity(i);
            }
        }
    }
}
