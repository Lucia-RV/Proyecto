package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {
    Button regresarMen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        regresarMen = (Button) findViewById(R.id.btnRegresar3);

        regresarMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent ( Main3Activity.this, Menu.class);
                startActivity(intent);

            }
        });
    }
}
