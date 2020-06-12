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

public class Main3Activity extends AppCompatActivity {
    Button regresarMen, registar, consultar,btnBES;
    EditText id, nombre, horas, dias, aguinaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        regresarMen = (Button) findViewById(R.id.btnRegresar3);
        registar = (Button) findViewById(R.id.btnGES);
        consultar = (Button) findViewById(R.id.btnCES);
        id = (EditText) findViewById(R.id.txtidestructura);
        nombre = (EditText) findViewById(R.id.txtnombreestructura);
        horas = (EditText) findViewById(R.id.txtcantidadhoras);
        dias = (EditText)findViewById(R.id.txtcantidaddias);
        aguinaldo = (EditText) findViewById(R.id.txtdiasaguinaldo);
        btnBES = (Button) findViewById(R.id.btnBES);

        btnBES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, BuscarrEstructura.class);
                startActivity(intent);
            }
        });


        regresarMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent ( Main3Activity.this, Menu.class);
                startActivity(intent);

            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new  Intent ( Main3Activity.this, ConsultaEsctructura.class);
            startActivity(intent);
        }
    });


        registar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            agregarusuario();
        }
    });
}

    public void agregarusuario()
    {
        try {
            PreparedStatement pst=conexionBD().prepareStatement("insert into Estructura_Salarial values(?,?,?,?,?)" );
            pst.setString(1,id.getText().toString());
            pst.setString(2,nombre.getText().toString());
            pst.setString(3,horas.getText().toString());
            pst.setString(4, dias.getText().toString());
            pst.setString(5,aguinaldo.getText().toString());
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
