package com.example.alquilercoches.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alquilercoches.R;
import com.example.alquilercoches.activities.RecoverActivity;

public class LoginFragment extends Fragment {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewRecover;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        textViewRecover = view.findViewById(R.id.textViewRecover);

        buttonLogin.setOnClickListener(v -> {
            // Lógica de inicio de sesión
        });

        textViewRecover.setOnClickListener(v -> {
            // Navegar a RecoverActivity
            Intent intent = new Intent(getActivity(), RecoverActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
