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

public class BuscarContrato extends AppCompatActivity {
    Button buscar;
    EditText buscador, TipoContrato, ClaveE, NomPlaza, Puesto, FechaInicio, FechaTermino, PeriodoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_contrato);

        buscador =(EditText) findViewById(R.id.txtbuscar);
        TipoContrato = (EditText) findViewById(R.id.txttcb);
        ClaveE = (EditText) findViewById(R.id.txtclaveeb);
        NomPlaza = (EditText) findViewById(R.id.txtdias);
        Puesto = (EditText) findViewById(R.id.txtpuestocb);
        FechaInicio = (EditText) findViewById(R.id.txtficb);
        FechaTermino = (EditText) findViewById(R.id.txtftbc);
        PeriodoPago = (EditText) findViewById(R.id.txtppbc);

        buscar = (Button) findViewById(R.id.btnbuscarc);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaContrato();
            }
        });
    }


    public void consultaContrato()
    {
        try{
            Statement stm  =conexionBD().createStatement();
            ResultSet rs  = stm.executeQuery(  "SELECT  * FROM Contrato where No_Contrato= '"+ buscador.getText().toString() +   " ' "  );
            if(rs.next())
            {
                TipoContrato.setText(rs.getString(2));
                ClaveE.setText(rs.getString(3));
                NomPlaza.setText(rs.getString(4));
                Puesto.setText(rs.getString(5));
                FechaInicio.setText(rs.getString(6));
                FechaTermino.setText(rs.getString(7));
                PeriodoPago.setText(rs.getString(8));


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
