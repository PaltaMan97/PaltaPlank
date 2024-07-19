package com.example.appeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appeventos.models.EventModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventsSavingActivity extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;
    Button addDateBtn, deleteAllBtn;
    String selectedDate;

//    MaterialToolbar toolbar;

//    List<EventModel> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_events_saving);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calendarView = findViewById(R.id.calendar_view);
        calendar = Calendar.getInstance();
        addDateBtn = findViewById(R.id.add_date_btn);
        selectedDate = "";
//        toolbar = findViewById(R.id.materialToolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Seleccion de Fecha");

//        events = new ArrayList<>();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selectedDate = day + "/" + (month+1) + "/" + year;
                Toast.makeText(EventsSavingActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
            }
        });

        addDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // aqui navegaremos a otra lista, con el formulario correspondiente para el evento.
                // enviaremos la fecha seleccionada como parametro.
                Intent intent = new Intent(EventsSavingActivity.this, EventRegisterActivity.class);
                Bundle extras = new Bundle();
                extras.putString("selectedDate", selectedDate);
                intent.putExtras(extras);
                EventsSavingActivity.this.startActivity(intent);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}