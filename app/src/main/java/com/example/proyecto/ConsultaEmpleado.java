package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConsultaEmpleado extends AppCompatActivity {
    ListView lista;


    ArrayList<String> title = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_empleado);

        lista = (ListView) findViewById(R.id.listEmpleado);
        PreparedStatement pst;
        Statement stmt = null;
        try {
            stmt = conexionBD().createStatement();
            ResultSet rs = stmt.executeQuery("select * from Empleado");
            title.clear();
            final ArrayList<Empleado> list = new ArrayList<Empleado>();
            while (rs.next())
            {
                Empleado obj;
                obj = new Empleado();
                obj.ClaveE = rs.getString("Clave_E");
                obj.NombreE = rs.getString("Nombre_E");
                obj.ApellidoP = rs.getString("Apellido_Paterno");
                obj.ApellidoM = rs.getString("Apellido_Materno");
                obj.FechaN = rs.getString("Fecha_Nacimiento");
                obj.Curp = rs.getString("Curp");
                obj.RFC= rs.getString("RFC");
                obj.TelF = rs.getString("Tel_Fijo");
                obj.Direccion = rs.getString("Direccion");
                obj.NoI  = rs.getString("No_Interior");
                obj.NoE = rs.getString("No_Exterior");
                obj.Colonia = rs.getString("Colonia");
                obj.Municipio = rs.getString("Municipio");
                obj.Estado= rs.getString("Estado");
                obj.CP  = rs.getString("CP");
                obj.Genero = rs.getString("Genero");
                obj.EstadoC  = rs.getString("Estado_Civil");
                obj.NoH = rs.getString("No_Hijos");
                obj.GradoE= rs.getString("Grado_Escolar");
                obj.CuentaB  = rs.getString("Cuenta_Bancaria");
                obj.ClaveI = rs.getString("Clave_Interbancaria");
                list.add(obj);
                title.add(obj.ClaveE+"\t" + obj.NombreE+"\t" + obj.ApellidoP+"\t" + obj.ApellidoM+"\t" + obj.FechaN+"\t"
                + obj.Curp+"\t" + obj.RFC+"\t" + obj.TelF+"\t" + obj.Direccion+"\t" + obj.NoI+"\t" + obj.NoE+"\t" + obj.Colonia+"\t" +
                obj.Municipio+"\t" + obj.Estado+"\t" + obj.CP+"\t" + obj.Genero+"\t" + obj.EstadoC+"\t" + obj.NoH+"\t" +
                obj.GradoE+"\t" + obj.CuentaB+"\t" + obj.ClaveI);


            }
            arrayAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, title);
            lista.setAdapter(arrayAdapter);



            arrayAdapter.notifyDataSetInvalidated();
            lista.invalidateViews();
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String aaa = title.get(position).toString();
                    Empleado c = list.get(position);
                    Intent i = new Intent(getApplicationContext(),EditEmpleado.class);
                    i.putExtra("ClaveE", c.ClaveE);
                    i.putExtra("NombreE", c.NombreE );
                    i.putExtra("ApellidoP", c.ApellidoP);
                    i.putExtra("ApellidoM", c.ApellidoM );
                    i.putExtra("FechaN", c.FechaN);
                    i.putExtra("Curp", c.Curp);
                    i.putExtra("RFC", c.RFC);
                    i.putExtra("TelF", c.TelF);
                    i.putExtra("Direccion", c.Direccion);
                    i.putExtra("NoI", c.NoI);
                    i.putExtra("NoE", c.NoE);
                    i.putExtra("Colonia", c.Colonia);
                    i.putExtra("Municipio", c.Municipio);
                    i.putExtra("Estado", c.Estado);
                    i.putExtra("CP", c.CP);
                    i.putExtra("Genero", c.Genero);
                    i.putExtra("EstadoC", c.EstadoC);
                    i.putExtra("NoH", c.NoH);
                    i.putExtra("GradoE", c.GradoE);
                    i.putExtra("CuentaB", c.CuentaB);
                    i.putExtra("ClaveI", c.ClaveI);
                    startActivity(i);

                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
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
