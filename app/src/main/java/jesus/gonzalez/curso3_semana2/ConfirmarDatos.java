package jesus.gonzalez.curso3_semana2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ConfirmarDatos extends AppCompatActivity {

    private String nombre;
    private String fechaDeNacimiento;
    private String telefono;
    private String email;
    private String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        TextInputEditText tfNombre = (TextInputEditText) findViewById(R.id.tfName_1);
        TextInputEditText tfDatePicker = (TextInputEditText) findViewById(R.id.tfDatePicker);
        TextInputEditText tfTelefono = (TextInputEditText) findViewById(R.id.tfTelefonoET);
        TextInputEditText tfEmail = (TextInputEditText) findViewById(R.id.tfEmail_1);
        TextInputEditText tfDescripcionContacto = (TextInputEditText) findViewById(R.id.tfDescripcionContacto_1);

        Bundle extras = getIntent().getExtras();

        nombre = extras.getString("nombre");
        fechaDeNacimiento = extras.getString("fechaDeNacimiento");
        telefono = extras.getString("telefono");
        email = extras.getString("email");
        descripcion = extras.getString("descripcion");

        tfNombre.setHint("Nombre: " +  nombre);
        tfDatePicker.setHint("Fecha de Nacimiento: " + fechaDeNacimiento);
        tfTelefono.setHint("Telefono: " + telefono);
        tfEmail.setHint("Email: " + email);
        tfDescripcionContacto.setHint("Descripcion: " + descripcion);

        Button botonSiguiente = (Button) findViewById(R.id.btnSiguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToConfirmScreen = new Intent(ConfirmarDatos.this, MainActivity.class);

                intentToConfirmScreen.putExtra("nombre", nombre);
                intentToConfirmScreen.putExtra("fechaDeNacimiento", fechaDeNacimiento);
                intentToConfirmScreen.putExtra("email", email);
                intentToConfirmScreen.putExtra("descripcion", descripcion);
                intentToConfirmScreen.putExtra("telefono", telefono);

                startActivity(intentToConfirmScreen);
                finish();
            }
        });
    }
}
