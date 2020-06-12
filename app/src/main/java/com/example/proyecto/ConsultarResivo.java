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

public class ConsultarResivo extends AppCompatActivity {
    ListView lista;

    ArrayList<String> title = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_resivo);


        lista = (ListView) findViewById(R.id.listviewResivo);
        PreparedStatement pst;
        Statement stmt = null;
        try {
            stmt = conexionBD().createStatement();
            ResultSet rs = stmt.executeQuery("select * from ResivoNomina");
            title.clear();
            final ArrayList<ResivoNomina> list = new ArrayList<ResivoNomina>();
            while (rs.next())
            {
                ResivoNomina obj;
                obj = new ResivoNomina();
                obj.NoResivo = rs.getString("No_Recibo");
                obj.ClaveE = rs.getString("Clave_E");
                obj.NoContrato = rs.getString("No_Contrato");
                obj.NoPlaza = rs.getString("No_Plaza");
                obj.PeriodoResivo = rs.getString("Periodo_Recibo");
                obj.TipoPago = rs.getString("Tipo_Pago");
                list.add(obj);
                title.add(obj.NoResivo+"\t" + obj.ClaveE+"\t"+ obj.NoContrato+"\t" + obj.NoPlaza+"\t" + obj.PeriodoResivo+"\t" + obj.TipoPago);


            }
            arrayAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, title);
            lista.setAdapter(arrayAdapter);



            arrayAdapter.notifyDataSetInvalidated();
            lista.invalidateViews();
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String aaa = title.get(position).toString();
                    ResivoNomina c = list.get(position);
                    Intent i = new Intent(getApplicationContext(), EditResivo.class);
                    i.putExtra("NoResivo", c.NoResivo);
                    i.putExtra("ClaveE", c.ClaveE );
                    i.putExtra("NoContrato", c.NoContrato);
                    i.putExtra("NoPlaza", c.NoPlaza );
                    i.putExtra("PeriodoResivo", c.PeriodoResivo);
                    i.putExtra("TipoPago", c.TipoPago);
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
