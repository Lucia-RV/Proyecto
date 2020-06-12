package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarrEstructura extends AppCompatActivity {
EditText buscador, nombre, horas, dias, agui;
Button buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarr_estructura);

        buscador =(EditText) findViewById(R.id.txtbuscar);
        nombre = (EditText) findViewById(R.id.txtnombre);
        horas = (EditText) findViewById(R.id.txthoras);
        dias = (EditText) findViewById(R.id.txtdias);
        agui = (EditText) findViewById(R.id.txtaguinaldo);

        buscar = (Button) findViewById(R.id.btnbuscarestruc);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaEstructura();
            }
        });
    }


    public void consultaEstructura()
    {
        try{
            Statement stm  =conexionBD().createStatement();
            ResultSet rs  = stm.executeQuery(  "SELECT  * FROM Estructura_Salarial where No_Estructura= '"+ buscador.getText().toString() +   " ' "  );
            if(rs.next())
            {
                nombre.setText(rs.getString(2));
                horas.setText(rs.getString(3));
                dias.setText(rs.getString(4));
                agui.setText(rs.getString(5));


            }

        }catch (SQLException e){
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
