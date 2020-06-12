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

public class Main5Activity extends AppCompatActivity {

    Button regresarmn, registrar, consultar,buscar;
    EditText NoNomina, ClaveE, Infonavit, SeguroSocial, Indemnizacion, HorasExtras, Incapacidad, Liquidaciones, Antiguedad,BonoAtencion, BonoPuntualidad, SalarioFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        regresarmn = (Button) findViewById(R.id.btnRegresar5);
        registrar = (Button) findViewById(R.id.btnGN);
        consultar = (Button) findViewById(R.id.btnCN);
        buscar = (Button) findViewById(R.id.btnBN);

        NoNomina = (EditText) findViewById(R.id.nonomina);
        ClaveE= (EditText) findViewById(R.id.claveenomina);
        Infonavit = (EditText) findViewById(R.id.infonavit);
        SeguroSocial = (EditText) findViewById(R.id.segurosocial);
        Indemnizacion = (EditText) findViewById(R.id.indemnizacion);
        HorasExtras = (EditText) findViewById(R.id.horasextras);
        Incapacidad = (EditText) findViewById(R.id.incapasidad);
        Liquidaciones = (EditText) findViewById(R.id.liquidacion);
        Antiguedad = (EditText) findViewById(R.id.antiguedad);
        BonoAtencion= (EditText) findViewById(R.id.batencion);
        BonoPuntualidad = (EditText) findViewById(R.id.bpuntualidad);
        SalarioFinal = (EditText) findViewById(R.id.salariofinal);

        regresarmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main5Activity.this, Menu.class);
                startActivity(intent);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main5Activity.this,ConsultarNomina.class);
                startActivity(intent);
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main5Activity.this,BuscarNomina.class);
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
            PreparedStatement pst=conexionBD().prepareStatement("insert into Nomina values(?,?,?,?,?,?,?,?,?,?,?,?,?)" );
            pst.setString(1,NoNomina.getText().toString());
            pst.setString(2,ClaveE.getText().toString());
            pst.setString(3,Infonavit.getText().toString());
            pst.setString(4,SeguroSocial.getText().toString());
            pst.setString(5,Indemnizacion.getText().toString());
            pst.setString(6,HorasExtras.getText().toString());
            pst.setString(7,Incapacidad.getText().toString());
            pst.setString(8,Liquidaciones.getText().toString());
            pst.setString(9,Antiguedad.getText().toString());
            pst.setString(10,BonoAtencion.getText().toString());
            pst.setString(11,BonoPuntualidad.getText().toString());
            pst.setString(12,SalarioFinal.getText().toString());
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
