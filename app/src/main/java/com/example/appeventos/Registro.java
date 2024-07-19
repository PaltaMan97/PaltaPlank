package com.example.appeventos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Registro extends AppCompatActivity {
    private TextInputLayout tilUserRegister, tilPassRegister, tilRespuesta;
    private Button btnRegistrado;
    private RadioGroup rdPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        referencias();
        eventos();

    }

    private void referencias(){
        tilUserRegister = findViewById(R.id.tilUserRegister);
        tilPassRegister = findViewById(R.id.tilPassRegister);
        btnRegistrado = findViewById(R.id.btnRegistrado);
        rdPreguntas = findViewById(R.id.rdPreguntas);
        tilRespuesta = findViewById(R.id.tilRespuesta);
    }

    private void eventos(){
        btnRegistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String newUsername = etNewUsername.getText().toString();
//                String newPassword = etNewPassword.getText().toString();
//                if (registerUser(newUsername, newPassword)) {
//                    finish();
//                } else {
//                    tilNewPassword.setError(getString(R.string.error_registering_user));
//                }
            }
        });

    }

}