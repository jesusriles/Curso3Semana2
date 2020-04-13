package jesus.gonzalez.curso3_semana2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextInputEditText tfNombre;
    private TextInputEditText tfDatePicker;
    private TextInputEditText tfTelefono;
    private TextInputEditText tfEmail;
    private TextInputEditText tfDescripcionContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tfNombre = (TextInputEditText) findViewById(R.id.tfName_1);
        tfDatePicker = (TextInputEditText) findViewById(R.id.tfDatePicker);
        tfTelefono = (TextInputEditText) findViewById(R.id.tfTelefonoET);
        tfEmail = (TextInputEditText) findViewById(R.id.tfEmail_1);
        tfDescripcionContacto = (TextInputEditText) findViewById(R.id.tfDescripcionContacto_1);

        createCalendarPicker();

        try {
            Bundle extras = getIntent().getExtras();
            Toast.makeText(this, extras.getString("nombre"), Toast.LENGTH_SHORT).show();

            tfNombre.setText(extras.getString("nombre"));
            tfDatePicker.setText(extras.getString("fechaDeNacimiento"));
            tfTelefono.setText(extras.getString("telefono"));
            tfEmail.setText(extras.getString("email"));
            tfDescripcionContacto.setText(extras.getString("descripcion"));
        } catch (Exception e) {
            Log.i("MainActivity", "Error: 00001");
        }


        Button botonSiguiente = (Button) findViewById(R.id.btnSiguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String nombre = tfNombre.getText().toString();
                String fechaDeNacimiento = tfDatePicker.getText().toString();
                String telefono = tfTelefono.getText().toString();
                String email = tfEmail.getText().toString();
                String descripcion = tfDescripcionContacto.getText().toString();

                Intent intentToConfirmScreen = new Intent(MainActivity.this, ConfirmarDatos.class);

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

    // Create the calendar
    public void createCalendarPicker() {

        mDisplayDate = (TextView) findViewById(R.id.tfDatePicker);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        mDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }
}
