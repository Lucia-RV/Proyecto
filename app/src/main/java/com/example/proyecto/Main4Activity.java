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

public class Main4Activity extends AppCompatActivity {
    Button regresarMM,registrar, consultar, buscar;
    EditText NoContrato, TipoContrato, ClaveE, NomPlaza, Puesto, FechaInicio, FechaTermino, PeriodoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        regresarMM = (Button) findViewById(R.id.btnRegresar4);
        registrar = (Button) findViewById(R.id.btnGCON);
        consultar = (Button) findViewById(R.id.btnCCON);
        buscar  = (Button) findViewById(R.id.btnBCON);

        NoContrato = (EditText) findViewById(R.id.txtcontrato);
        TipoContrato = (EditText) findViewById(R.id.txttipocontrato);
        ClaveE = (EditText) findViewById(R.id.txtclaveec);
        NomPlaza = (EditText) findViewById(R.id.txtnombreplazac);
        Puesto = (EditText) findViewById(R.id.txtpuestoc);
        FechaInicio = (EditText) findViewById(R.id.txtfic);
        FechaTermino = (EditText) findViewById(R.id.txtftc);
        PeriodoPago = (EditText) findViewById(R.id.txtperiodopagoc);
        
        regresarMM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this, Menu.class);
                startActivity(intent);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,ConsultaContrato.class);
                startActivity(intent);
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this,BuscarContrato.class);
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
            PreparedStatement pst=conexionBD().prepareStatement("insert into Contratos values(?,?,?,?,?,?,?,?)" );
            pst.setString(1,NoContrato.getText().toString());
            pst.setString(2,TipoContrato.getText().toString());
            pst.setString(3,ClaveE.getText().toString());
            pst.setString(4,NomPlaza.getText().toString());
            pst.setString(5,Puesto.getText().toString());
            pst.setString(6,FechaInicio.getText().toString());
            pst.setString(7,FechaTermino.getText().toString());
            pst.setString(8,PeriodoPago.getText().toString());
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
