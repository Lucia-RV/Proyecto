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

public class ConsultarNomina extends AppCompatActivity {
    ListView lista;


    ArrayList<String> title = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_nomina);

        lista = (ListView) findViewById(R.id.listviewNomina);
        PreparedStatement pst;
        Statement stmt = null;
        try {
            stmt = conexionBD().createStatement();
            ResultSet rs = stmt.executeQuery("select * from Nomina");
            title.clear();
            final ArrayList<Nomina> list = new ArrayList<Nomina>();
            while (rs.next())
            {
                Nomina obj;
                obj = new Nomina();
                obj.NoNomina= rs.getString("No_Nomina");
                obj.ClaveE = rs.getString("Clave_E");
                obj.Infonavit = rs.getString("Infonavit");
                obj.SeguroSocial = rs.getString("Seguro_Social ");
                obj.Indemnizacion = rs.getString("Indemnizacion");
                obj.HorasExtras = rs.getString("Horas_Extras");
                obj.Incapacidad = rs.getString("Incapacidad");
                obj.Liquidaciones = rs.getString("Liquidaciones");
                obj.Antiguedad = rs.getString("Antiguedad");
                obj.BonoAtencion = rs.getString("Bono_Atencion");
                obj.BonoPuntualidad = rs.getString("Bono_Puntualidad");
                obj.SalarioFinal = rs.getString("SalarioFinal ");
                list.add(obj);
                title.add(obj.NoNomina+"\t" + obj.ClaveE+"\t"+ obj.Infonavit+"\t" + obj.SeguroSocial+"\t" + obj.Indemnizacion+"\t" + obj.HorasExtras
                        +"\t" + obj.Incapacidad+"\t" + obj.Liquidaciones+"\t" + obj.Antiguedad+"\t" + obj.BonoAtencion+"\t" + obj.BonoPuntualidad+"\t" + obj.SalarioFinal);


            }
            arrayAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, title);
            lista.setAdapter(arrayAdapter);



            arrayAdapter.notifyDataSetInvalidated();
            lista.invalidateViews();
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String aaa = title.get(position).toString();
                    Nomina c = list.get(position);
                    Intent i = new Intent(getApplicationContext(), EditNomina.class);
                    i.putExtra("NoNomina", c.NoNomina);
                    i.putExtra("ClaveE", c.ClaveE );
                    i.putExtra("Infonavit", c.Infonavit);
                    i.putExtra("SeguroSocial", c.SeguroSocial );
                    i.putExtra("Indemnizacion", c.Indemnizacion);
                    i.putExtra("HorasExtras", c.HorasExtras);
                    i.putExtra("Incapacidad", c.Incapacidad);
                    i.putExtra("Liquidaciones", c.Liquidaciones);
                    i.putExtra("Antiguedad", c.Antiguedad);
                    i.putExtra("BonoAtencion", c.BonoAtencion);
                    i.putExtra("BonoPuntualidad", c.BonoPuntualidad);
                    i.putExtra("SalarioFinal", c.SalarioFinal);
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
