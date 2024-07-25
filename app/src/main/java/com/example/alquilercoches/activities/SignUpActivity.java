package com.example.alquilercoches.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alquilercoches.R;
import com.example.alquilercoches.controllers.WebService;

public class SignUpActivity extends AppCompatActivity {

    //Declarar vistas
    private EditText editTextDNI;
    private EditText editTextName;
    private EditText editTextSurnames;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inicializamos las vistas
        editTextDNI = findViewById(R.id.editTextDNI);
        editTextName = findViewById(R.id.editTextName);
        editTextSurnames = findViewById(R.id.editTextSurnames);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        error = findViewById(R.id.error);

        // Configuración del escuchador del botón de registro para crear un nuevo usuario cuando se hace clic en el botón
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    //Registrar un nuevo user
    private void registerUser() {

        //Obtener datos introducidos por el user
        String dni = editTextDNI.getText().toString();
        String name = editTextName.getText().toString();
        String surnames = editTextSurnames.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        boolean incompleto=false;
        boolean campoComun=(dni.isEmpty() || name.isEmpty() || surnames.isEmpty() || email.isEmpty() || password.isEmpty());

        if (!campoComun){
            error.setText("");

            //recuperar datos
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = WebService.RAIZ + WebService.SIGNUP + "?"
                    + "email=" + email
                    + "&clave=" + password
                    + "&nombre=" + name
                    + "&apellidos=" + surnames
                    + "&dni=" + dni;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Aquí manejas la respuesta del servidor
                            int numero = Integer.parseInt(response);
                            if (numero==0){
                                error.setText("Correo o DNI ya registrado");
                            } else if (numero==1){
                                Toast.makeText(getApplicationContext(), "El usuario se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "No se ha podido guardar el usuario, no preguntes por qué", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignUpActivity.this, "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            queue.add(stringRequest);

        } else {
            error.setText("Rellena todos los campos");
        }
    }
}
