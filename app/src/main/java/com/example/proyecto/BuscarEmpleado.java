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

public class BuscarEmpleado extends AppCompatActivity {
    EditText ClaveE,NombreE,ApellidoP,ApellidoM,FechaN,Curp,RFC,TelF,Direccion,NoI,NoE,Colonia,Municipio,Estado,CP,Genero,EstadoC,NoH,GradoE,CuentaB,ClaveI;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_empleado);

        ClaveE = (EditText) findViewById(R.id.txtclavee);
        NombreE = (EditText)findViewById(R.id.txtnombree);
        ApellidoP = (EditText)findViewById(R.id.txtapellidop);
        ApellidoM = (EditText) findViewById(R.id.txtapellidom);
        FechaN = (EditText)findViewById(R.id.txtfn);
        Curp = (EditText)findViewById(R.id.txtcurp);
        RFC = (EditText)findViewById(R.id.txtrfc);
        TelF = (EditText)findViewById(R.id.txttf);
        Direccion = (EditText)findViewById(R.id.txtdireccion);
        NoI =(EditText)findViewById(R.id.txtni);
        NoE = (EditText)findViewById(R.id.txtne);
        Colonia = (EditText)findViewById(R.id.txtcolonia);
        Municipio = (EditText) findViewById(R.id.txtmunicipio);
        Estado = (EditText)findViewById(R.id.txtestado);
        CP = (EditText)findViewById(R.id.txtcp);
        Genero = (EditText)findViewById(R.id.txtgenero);
        EstadoC = (EditText) findViewById(R.id.txtec);
        NoH = (EditText) findViewById(R.id.txtnh);
        GradoE = (EditText) findViewById(R.id.txtgradoescolar);
        CuentaB = (EditText) findViewById(R.id.txtcb);
        ClaveI = (EditText) findViewById(R.id.txtci);

        buscar = (Button) findViewById(R.id.btnbuscare);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaEmpleado();
            }
        });
    }


    public void consultaEmpleado()
    {
        try{
            Statement stm  =conexionBD().createStatement();
            ResultSet rs  = stm.executeQuery(  "SELECT  * FROM Empleado where Clave_E= '"+ buscar.getText().toString() +   " ' "  );
            if(rs.next())
            {
                NombreE.setText(rs.getString(2));
                ApellidoP.setText(rs.getString(3));
                ApellidoM.setText(rs.getString(4));
                FechaN.setText(rs.getString(5));
                Curp.setText(rs.getString(6));
                RFC.setText(rs.getString(7));
                TelF.setText(rs.getString(8));
                Direccion.setText(rs.getString(9));
                NoI.setText(rs.getString(10));
                NoE.setText(rs.getString(11));
                Colonia.setText(rs.getString(12));
                Municipio.setText(rs.getString(13));
                Estado.setText(rs.getString(14));
                CP.setText(rs.getString(15));
                Genero.setText(rs.getString(16));
                EstadoC.setText(rs.getString(17));
                NoH.setText(rs.getString(18));
                GradoE.setText(rs.getString(19));
                CuentaB.setText(rs.getString(20));
                ClaveI.setText(rs.getString(21));


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
