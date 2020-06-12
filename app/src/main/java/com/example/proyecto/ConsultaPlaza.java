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

public class ConsultaPlaza extends AppCompatActivity {
    ListView lista;

    ArrayList<String> title = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_plaza);

        lista = (ListView) findViewById(R.id.listPlaza);
        PreparedStatement pst;
        Statement stmt = null;
        try {
            stmt = conexionBD().createStatement();
            ResultSet rs = stmt.executeQuery("select * from Plaza");
            title.clear();
            final ArrayList<Plaza> list = new ArrayList<Plaza>();
            while (rs.next())
            {
                Plaza obj;
                obj = new Plaza();
                obj.noplaza = rs.getString("No_Plaza");
                obj.nomplaza = rs.getString("Nombre_Plaza");
                obj.departamento = rs.getString("Departamento");
                obj.salario = rs.getString("Salario");
                obj.noestructura = rs.getString("No_Estructura");
                list.add(obj);
                title.add(obj.noplaza+"\t" + obj.nomplaza+"\t"+ obj.departamento+"\t"+obj.salario+"\t"+obj.noestructura);


            }
            arrayAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, title);
            lista.setAdapter(arrayAdapter);



            arrayAdapter.notifyDataSetInvalidated();
            lista.invalidateViews();
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String aaa = title.get(position).toString();
                    Plaza c = list.get(position);
                    Intent i = new Intent(getApplicationContext(), EditPlaza.class);
                    i.putExtra("noplaza", c.noplaza);
                    i.putExtra("nomplaza", c.nomplaza );
                    i.putExtra("departamento", c.departamento);
                    i.putExtra("salario", c.salario );
                    i.putExtra("noestructura", c.noestructura);
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
