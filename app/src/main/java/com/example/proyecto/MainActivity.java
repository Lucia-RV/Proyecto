package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {
Button regresarMenu, registrar, consultar,buscar;
    EditText ClaveE,NombreE,ApellidoP,ApellidoM,FechaN,Curp,RFC,TelF,Direccion,NoI,NoE,Colonia,Municipio,Estado,CP,Genero,EstadoC,NoH,GradoE,CuentaB,ClaveI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regresarMenu = (Button) findViewById(R.id.btnregresar);
        consultar = (Button) findViewById(R.id.btnCE);
        registrar = (Button) findViewById(R.id.btnGE);
        buscar = (Button)findViewById(R.id.btnBE);

        ClaveE = (EditText) findViewById(R.id.clavee);
        NombreE = (EditText)findViewById(R.id.nombree);
        ApellidoP = (EditText)findViewById(R.id.apellidope);
        ApellidoM = (EditText) findViewById(R.id.apellidome);
        FechaN = (EditText)findViewById(R.id.fn);
        Curp = (EditText)findViewById(R.id.curp);
        RFC = (EditText)findViewById(R.id.rfc);
        TelF = (EditText)findViewById(R.id.telfijo);
        Direccion = (EditText)findViewById(R.id.direccion);
        NoI =(EditText)findViewById(R.id.noin);
        NoE = (EditText)findViewById(R.id.noex);
        Colonia = (EditText)findViewById(R.id.colonia);
        Municipio = (EditText) findViewById(R.id.municipio);
        Estado = (EditText)findViewById(R.id.estado);
        CP = (EditText)findViewById(R.id.cp);
        Genero = (EditText)findViewById(R.id.genero);
        EstadoC = (EditText) findViewById(R.id.estadocivil);
        NoH = (EditText) findViewById(R.id.nohijos);
        GradoE = (EditText) findViewById(R.id.gradoesc);
        CuentaB = (EditText) findViewById(R.id.cuentaban);
        ClaveI = (EditText) findViewById(R.id.claveinter);


        regresarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, Menu.class);
                startActivity(intent);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConsultaEmpleado.class);
                startActivity(intent);
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BuscarEmpleado.class);
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
            PreparedStatement pst= conexionBD ().prepareStatement("insert into Empleado values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );
            pst.setString(1,ClaveE.getText().toString());
            pst.setString(2,NombreE.getText().toString());
            pst.setString(3,ApellidoP.getText().toString());
            pst.setString(4,ApellidoM.getText().toString());
            pst.setString(5,FechaN.getText().toString());
            pst.setString(6,Curp.getText().toString());
            pst.setString(7,RFC.getText().toString());
            pst.setString(8,TelF.getText().toString());
            pst.setString(9,Direccion.getText().toString());
            pst.setString(10,NoI.getText().toString());
            pst.setString(11,NoE.getText().toString());
            pst.setString(12,Colonia.getText().toString());
            pst.setString(13,Municipio.getText().toString());
            pst.setString(14,Estado.getText().toString());
            pst.setString(15,CP.getText().toString());
            pst.setString(16,Genero.getText().toString());
            pst.setString(17,EstadoC.getText().toString());
            pst.setString(18,NoH.getText().toString());
            pst.setString(19,GradoE.getText().toString());
            pst.setString(20,CuentaB.getText().toString());
            pst.setString(21,ClaveI.getText().toString());
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


