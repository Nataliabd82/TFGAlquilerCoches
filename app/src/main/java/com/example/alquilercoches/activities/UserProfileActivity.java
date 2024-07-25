package com.example.alquilercoches.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alquilercoches.R;
import com.example.alquilercoches.util.Util;

public class UserProfileActivity extends AppCompatActivity {

    private EditText editTextDNI;
    private EditText editTextName;
    private EditText editTextSurnames;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button backButton;
    private Button saveButton;
    private SharedPreferences prefs;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Inicializa las vistas
        editTextDNI = findViewById(R.id.editTextDNI);
        editTextName = findViewById(R.id.editTextName);
        editTextSurnames = findViewById(R.id.editTextSurnames);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        backButton = findViewById(R.id.buttonBack);
        saveButton = findViewById(R.id.buttonModify);
        error = findViewById(R.id.errorModify);

        // cargar los datos del usuario en las vistas
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredentialsIfExist();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify();
            }
        });
    }

    private void modify() {
        //Obtener datos introducidos por el user
        String dni = editTextDNI.getText().toString();
        String name = editTextName.getText().toString();
        String surnames = editTextSurnames.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        boolean incompleto = dni.isEmpty() || name.isEmpty() || surnames.isEmpty() || email.isEmpty();

        if (!incompleto) {
            error.setText("");
            String url = "https://tu-api.com/modify?dni=" + dni + "&name=" + name + "&surnames=" + surnames + "&email=" + email + "&password=" + password;

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Aquí manejas la respuesta del servidor
                            int numero = Integer.parseInt(response);
                            if (numero == 0) {
                                error.setText("Correo o DNI ya registrado");
                            } else {
                                saveOnPreferences(email, password, name, surnames, dni);
                                Toast.makeText(getApplicationContext(), "El usuario se ha modificado correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UserProfileActivity.this, "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
            queue.add(stringRequest);

        } else {
            error.setText("Rellena todos los campos");
        }
    }

    private void saveOnPreferences(String email, String password, String name, String surnames, String dni) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("name", name);
        editor.putString("surnames", surnames);
        editor.putString("dni", dni);
        editor.apply();
    }

    private void setCredentialsIfExist() {
        String nombre = Util.getUserNombrePrefs(prefs);
        String apellidos = Util.getUserApellidosPrefs(prefs);
        String email = Util.getUserMailPrefs(prefs);
        String dni = Util.getUserDniPrefs(prefs);

        if (!nombre.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !dni.isEmpty()) {
            editTextName.setText(nombre);
            editTextSurnames.setText(apellidos);
            editTextEmail.setText(email);
            editTextDNI.setText(dni);
        }
    }
}
