package com.example.appeventos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout tilUser, tilPass;
    private Button btnIngresar, btnRegistrar;
    private TextView tvRecuperacion;
    private DataBaseHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper= new DataBaseHelper(this);
        DBHelper.getReadableDatabase();

        referencias();
        eventos();

    }

    private void referencias() {
        tilUser = findViewById(R.id.tilUser);
        tilPass = findViewById(R.id.tilPass);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        tvRecuperacion = findViewById(R.id.tvRecuperacion);
    }

    private void eventos() {
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tilUser.getEditText().getText().toString();
                String password = tilPass.getEditText().getText().toString();

            }

        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intentReg);
            }
        });

    }
}

