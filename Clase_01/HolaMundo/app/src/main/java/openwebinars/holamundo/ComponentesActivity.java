package openwebinars.holamundo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class ComponentesActivity extends AppCompatActivity {
    CheckBox checkboxCondiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes);

    }

    public void onCheckboxClicked(View v) {
        CheckBox check = (CheckBox)v;
        // check.isChecked();
        // este método nos permite conocer si el checkbox
        // ha sido chequeado o no

        switch (v.getId()) {
            case R.id.checkBoxCondiciones:
                Toast.makeText(ComponentesActivity.this,"Click en condiciones", Toast.LENGTH_SHORT).show(); break;
            case R.id.checkBoxSexo:
                Toast.makeText(ComponentesActivity.this,"Click en sexo", Toast.LENGTH_SHORT).show(); break;

        }
    }
}
