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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alquilercoches.R;
import com.example.alquilercoches.controllers.WebService;
import com.example.alquilercoches.model.User;
import com.example.alquilercoches.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewRegister;
    private TextView textViewRecover;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewRegister = findViewById(R.id.textViewRegister);
        textViewRecover = findViewById(R.id.textViewRecover);

        //se auto-rellenan el email y contraseña en caso de haberse guardado
        prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        setCredentialsIfExist();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a SignUpActivity
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        textViewRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a RecoverActivity
                Intent intent = new Intent(LoginActivity.this, RecoverActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        //recuperar datos
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = WebService.RAIZ +  WebService.LOGIN + "?email=" + email + "&password=" + password;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                try {
                    if(response.length()>0) {
                        jsonObject = response.getJSONObject(0);
                        String nombre = jsonObject.getString("nombre");
                        String apellidos = jsonObject.getString("apellidos");
                        String dni = jsonObject.getString("dni");
                        String rol = jsonObject.getString("rol");

                        User user = new User(email, password, nombre, apellidos, dni, rol);
                        saveOnPreferences(user);
                        Toast.makeText(getApplicationContext(), "Usuario seleccionado " + nombre, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "ERROR: No se encontro el usuario", Toast.LENGTH_LONG).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    //método que fija el email y contraseña que se hayan guardado
    private void setCredentialsIfExist() {
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserClavePrefs(prefs);
        if (!email.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    //método que guarda el email y contraseña introducidos
    private void saveOnPreferences(User user) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", user.getEmail());
        editor.putString("clave", user.getClave());
        editor.putString("nombre", user.getNombre());
        editor.putString("apellidos", user.getApellidos());
        editor.putString("dni", user.getDni());
        editor.putString("rol", user.getRol());
        editor.apply();
    }
}
