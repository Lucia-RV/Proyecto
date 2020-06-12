package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main6Activity extends AppCompatActivity {
        Button regresarmen, registrar, consultar,buscar;
    EditText NoResivo, ClaveE, NoContrato, NoPlaza, PeriodoResivo, TipoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        regresarmen = (Button) findViewById(R.id.btnRegresar6);
        registrar = (Button) findViewById(R.id.btnGR);
        consultar = (Button) findViewById(R.id.btnCR);
        buscar = (Button) findViewById(R.id.btnBR);

        NoResivo = (EditText) findViewById(R.id.noresivo);
        ClaveE = (EditText) findViewById(R.id.claveer);
        NoContrato = (EditText) findViewById(R.id.contrator);
        NoPlaza = (EditText) findViewById(R.id.plazar);
        PeriodoResivo = (EditText) findViewById(R.id.periodor);
        TipoPago = (EditText) findViewById(R.id.tipor);

        regresarmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main6Activity.this, Menu.class);
                startActivity(intent);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main6Activity.this,ConsultarResivo.class);
                startActivity(intent);
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main6Activity.this,BuscarResivo.class);
                startActivity(intent);
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarusuario();
            }
        });
    }

    public void agregarusuario()
    {
        try {
            PreparedStatement pst=conexionBD().prepareStatement("insert into Resivo_Nomina values(?,?,?,?,?,?)" );
            pst.setString(1,NoResivo.getText().toString());
            pst.setString(2,ClaveE.getText().toString());
            pst.setString(3,NoContrato.getText().toString());
            pst.setString(4,NoPlaza.getText().toString());
            pst.setString(5,PeriodoResivo.getText().toString());
            pst.setString(6,TipoPago.getText().toString());
            pst.executeUpdate();
            Toast.makeText(getApplicationContext(),"Registro Exitoso",Toast.LENGTH_SHORT).show();
        }catch (SQLException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public Connection conexionBD()
    {
        Conexion cn = new Conexion();



        Connection conexion =null;
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" +  cn.direccionip +":1433;databaseName="+ cn.BD+ ";user="+ cn.usuario+ ";password="+ cn.passwordusuario+";");

            // Toast.makeText(getApplicationContext(),"Conexion Exitosa",Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return  conexion;


    }
}
