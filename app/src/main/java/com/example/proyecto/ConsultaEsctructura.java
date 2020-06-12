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

public class ConsultaEsctructura extends AppCompatActivity {
    ListView lista;


    ArrayList<String> title = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_esctructura);

        lista = (ListView) findViewById(R.id.listviewEstructura);
        PreparedStatement pst;
        Statement stmt = null;
        try {
            stmt = conexionBD().createStatement();
            ResultSet rs = stmt.executeQuery("select * from Estructura_Salarial");
            title.clear();
            final ArrayList<Estructura_Salarial> list = new ArrayList<Estructura_Salarial>();
            while (rs.next())
            {
                Estructura_Salarial obj;
                obj = new Estructura_Salarial();
                obj.id = rs.getString("No_Estructura");
                obj.nombre = rs.getString("Nombre_Estructura");
                obj.horas = rs.getString("Cantidad_Horas");
                obj.dias = rs.getString("Cantidad_Dias");
                obj.aguinaldo = rs.getString("Dias_Aguinaldo");
                list.add(obj);
                title.add(obj.id+"\t" + obj.nombre+"\t"+ obj.horas);


            }
            arrayAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, title);
            lista.setAdapter(arrayAdapter);



            arrayAdapter.notifyDataSetInvalidated();
            lista.invalidateViews();
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String aaa = title.get(position).toString();
                    Estructura_Salarial c = list.get(position);
                    Intent i = new Intent(getApplicationContext(), Edit_Estructura_Salarial.class);
                    i.putExtra("id", c.id);
                    i.putExtra("nombre", c.nombre );
                    i.putExtra("horas", c.horas);
                    i.putExtra("dias", c.dias );
                    i.putExtra("aguinaldo", c.aguinaldo);
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
