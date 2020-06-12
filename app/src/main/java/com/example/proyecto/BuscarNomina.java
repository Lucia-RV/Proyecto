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

public class BuscarNomina extends AppCompatActivity {
    EditText buscador, ClaveE, Infonavit, SeguroSocial, Indemnizacion, HorasExtras, Incapacidad, Liquidaciones, Antiguedad,BonoAtencion, BonoPuntualidad, SalarioFinal;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_nomina);

        buscador = (EditText) findViewById(R.id.txtbuscar);
        ClaveE= (EditText) findViewById(R.id.txtclaveenb);
        Infonavit = (EditText) findViewById(R.id.txtinfonavitnb);
        SeguroSocial = (EditText) findViewById(R.id.txtssnb);
        Indemnizacion = (EditText) findViewById(R.id.txtindemnb);
        HorasExtras = (EditText) findViewById(R.id.txthexnb);
        Incapacidad = (EditText) findViewById(R.id.txtincanb);
        Liquidaciones = (EditText) findViewById(R.id.txtliquinb);
        Antiguedad = (EditText) findViewById(R.id.txtantinb);
        BonoAtencion= (EditText) findViewById(R.id.txtbonoanb);
        BonoPuntualidad = (EditText) findViewById(R.id.txtbonopnb);
        SalarioFinal = (EditText) findViewById(R.id.txtsalariofnb);

        buscar = (Button) findViewById(R.id.buscar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarNomina();
            }
        });
    }


    public void consultarNomina()
    {
        try{
            Statement stm  =conexionBD().createStatement();
            ResultSet rs  = stm.executeQuery(  "SELECT  * FROM Nomina where No_Nomina= '"+ buscador.getText().toString() +   " ' "  );
            if(rs.next())
            {
                ClaveE.setText(rs.getString(2));
                Infonavit.setText(rs.getString(3));
                SeguroSocial.setText(rs.getString(4));
                Indemnizacion.setText(rs.getString(5));
                HorasExtras.setText(rs.getString(6));
                Incapacidad.setText(rs.getString(7));
                Liquidaciones.setText(rs.getString(8));
                Antiguedad.setText(rs.getString(9));
                BonoAtencion.setText(rs.getString(10));
                BonoPuntualidad.setText(rs.getString(11));
                SalarioFinal.setText(rs.getString(12));

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
