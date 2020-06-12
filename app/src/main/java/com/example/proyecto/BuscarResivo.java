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

public class BuscarResivo extends AppCompatActivity {
    EditText buscador, ClaveE, NoContrato, NoPlaza, PeriodoResivo, TipoPago;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_resivo);

        buscador = (EditText) findViewById(R.id.buscar);
        ClaveE = (EditText) findViewById(R.id.claveer);
        NoContrato = (EditText) findViewById(R.id.txtnocontrator);
        NoPlaza = (EditText) findViewById(R.id.txtnoplazar);
        PeriodoResivo = (EditText) findViewById(R.id.txtppr);
        TipoPago = (EditText) findViewById(R.id.txttpr);

        buscar = (Button) findViewById(R.id.btnbuscar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarresivo();
            }
        });
    }


    public void consultarresivo()
    {
        try{
            Statement stm  =conexionBD().createStatement();
            ResultSet rs  = stm.executeQuery(  "SELECT  * FROM ResivoNomina where No_Resivo= '"+ buscador.getText().toString() +   " ' "  );
            if(rs.next())
            {
                ClaveE.setText(rs.getString(2));
                NoContrato.setText(rs.getString(3));
                NoPlaza.setText(rs.getString(4));
                PeriodoResivo.setText(rs.getString(5));
                TipoPago.setText(rs.getString(6));


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
