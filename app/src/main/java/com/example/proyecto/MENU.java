package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MENU extends AppCompatActivity {
Button empleado, salario, plaza, contratos, nomina, recibo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_e_n_u);

        empleado = (Button) findViewById(R.id.btnempleados);
        salario =(Button) findViewById(R.id.btnSalarial);
        plaza = (Button) findViewById(R.id.btnPlaza);
        contratos = (Button) findViewById(R.id.btnContrato);
        nomina = (Button) findViewById(R.id.btnNomina);
        recibo = (Button) findViewById(R.id.btnRecibo);

        empleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MENU.this, MainActivity.class);
                startActivity(intent);
            }
        });

        salario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MENU.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        plaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MENU.this, Main2Activity. class);
                startActivity(intent);
            }
        });

        contratos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MENU.this, Main4Activity.class);
                startActivity(intent);
            }
        });

        nomina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MENU.this, Main5Activity.class);
                startActivity(intent);
            }
        });

        recibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MENU.this, Main6Activity.class);
                startActivity(intent);
            }
        });

    }
}
