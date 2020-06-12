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

public class Main2Activity extends AppCompatActivity {
    Button regresarMenu,registar, consultar,buscar;

    EditText noplaza, nomplaza, departamento, salario, noestructura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        noplaza = (EditText) findViewById(R.id.txtplaza);
        nomplaza = (EditText) findViewById(R.id.txtnomplaza);
        departamento = (EditText) findViewById(R.id.txtdepartamentop);
        salario = (EditText) findViewById(R.id.txtsalariop);
        noestructura = (EditText) findViewById(R.id.txtestructurap);

        regresarMenu = (Button) findViewById(R.id.btnregresar);
        registar = (Button)findViewById(R.id.btnGE);
        consultar = (Button) findViewById(R.id.btnCP);
        buscar = (Button) findViewById(R.id.btnBP);

        regresarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Menu.class);
                startActivity(intent);
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,BuscarPlaza.class);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent ( Main2Activity.this, ConsultaEmpleado.class);
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
            PreparedStatement pst=conexionBD().prepareStatement("insert into Plaza values(?,?,?,?,?)" );
            pst.setString(1,noplaza.getText().toString());
            pst.setString(2,nomplaza.getText().toString());
            pst.setString(3,departamento.getText().toString());
            pst.setString(4,salario.getText().toString());
            pst.setString(5,noestructura.getText().toString());
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
