package com.salesianostriana.dam.pmdm.activityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final int ACTION_SUMAR = 1;
    public static final int ACTION_MULTIPLICAR = 2;

    Button btnSumar, btnMultiplicar;
    EditText op1, op2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSumar = (Button) findViewById(R.id.buttonSuma);
        btnMultiplicar = (Button) findViewById(R.id.buttonMultiplicar);

        op1 = (EditText) findViewById(R.id.editTextOp1);
        op2 = (EditText) findViewById(R.id.editTextOp2);

        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SumaActivity.class);
                i.putExtra("op1",op1.getText().toString());
                i.putExtra("op2",op2.getText().toString());

                startActivityForResult(i,ACTION_SUMAR);
            }
        });
    }
}
