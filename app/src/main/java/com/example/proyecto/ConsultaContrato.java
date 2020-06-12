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

public class ConsultaContrato extends AppCompatActivity {
    ListView lista;


    ArrayList<String> title = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_contrato);

        lista = (ListView) findViewById(R.id.listviewcontrato);
        PreparedStatement pst;
        Statement stmt = null;
        try {
            stmt = conexionBD().createStatement();
            ResultSet rs = stmt.executeQuery("select * from Contrato");
            title.clear();
            final ArrayList<Contrato> list = new ArrayList<Contrato>();
            while (rs.next())
            {
                Contrato obj;
                obj = new Contrato();
                obj.NoContrato = rs.getString("No_Contrato");
                obj.TipoContrato = rs.getString("Tipo_Contrato");
                obj.ClaveE = rs.getString("Clave_E");
                obj.NomPlaza = rs.getString("No_Plaza");
                obj.Puesto = rs.getString("Puesto");
                obj.FechaInicio = rs.getString("Fecha_Inicio ");
                obj.FechaTermino = rs.getString("Fecha_Termino");
                obj.PeriodoPago = rs.getString("Periodo_Pago");
                list.add(obj);
                title.add(obj.NoContrato+"\t" + obj.TipoContrato+"\t"+ obj.ClaveE+"\t"+ obj.NomPlaza+"\t"+ obj.Puesto
                        +"\t"+ obj.FechaInicio+"\t"+ obj.FechaTermino+"\t"+ obj.PeriodoPago);


            }
            arrayAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, title);
            lista.setAdapter(arrayAdapter);



            arrayAdapter.notifyDataSetInvalidated();
            lista.invalidateViews();
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String aaa = title.get(position).toString();
                    Contrato c = list.get(position);
                    Intent i = new Intent(getApplicationContext(), EditContrato.class);
                    i.putExtra("NoContrato", c.NoContrato);
                    i.putExtra("TipoContrato", c.TipoContrato );
                    i.putExtra("ClaveE", c.ClaveE);
                    i.putExtra("NomPlaza", c.NomPlaza );
                    i.putExtra("Puesto", c.Puesto);
                    i.putExtra("FechaInicio", c.FechaInicio);
                    i.putExtra("FechaTermino", c.FechaTermino);
                    i.putExtra("PeriodoPago", c.PeriodoPago);
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
