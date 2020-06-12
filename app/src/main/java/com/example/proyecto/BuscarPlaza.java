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

public class BuscarPlaza extends AppCompatActivity {
    EditText buscador, nomplaza, departamento, salario, noestructura;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_plaza);

        buscador =(EditText) findViewById(R.id.txtbuscar);
        nomplaza = (EditText) findViewById(R.id.txtnombreplazab);
        departamento = (EditText) findViewById(R.id.txtdepartamentob);
        salario = (EditText) findViewById(R.id.txtsalariob);
        noestructura = (EditText) findViewById(R.id.nomestructurab);

        buscar = (Button) findViewById(R.id.btnbuscarplaza);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaPlaza();
            }
        });
    }


    public void consultaPlaza()
    {
        try{
            Statement stm  =conexionBD().createStatement();
            ResultSet rs  = stm.executeQuery(  "SELECT  * FROM Plaza where Plaza= '"+ buscador.getText().toString() +   " ' "  );
            if(rs.next())
            {
                nomplaza.setText(rs.getString(2));
                departamento.setText(rs.getString(3));
                salario.setText(rs.getString(4));
                noestructura.setText(rs.getString(5));


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
