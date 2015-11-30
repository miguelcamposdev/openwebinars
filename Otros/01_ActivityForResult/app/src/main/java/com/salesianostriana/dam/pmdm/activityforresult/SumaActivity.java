package com.salesianostriana.dam.pmdm.activityforresult;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SumaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        int op1 = Integer.parseInt(extras.getString("op1"));
        int op2 = Integer.parseInt(extras.getString("op2"));

        if(op1.equals(null) || op2.equals(null)) {
            setResult(RESULT_CANCELED);
        } else {
            setResult(RESULT_OK);
        }


    }
}
