package com.example.alquilercoches.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alquilercoches.R;

public class RegisterFragment extends Fragment {

    private EditText editTextName;
    private EditText editTextSurnames;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextDNI;
    private Button buttonRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextSurnames = view.findViewById(R.id.editTextSurnames);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextDNI = view.findViewById(R.id.editTextDNI);
        buttonRegister = view.findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(v -> {
            // Handle registration logic here
        });

        return view;
    }
}
