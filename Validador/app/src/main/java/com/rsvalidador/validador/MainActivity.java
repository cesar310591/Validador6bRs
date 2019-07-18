package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.validador.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MainActivity extends AppCompatActivity {
TextView ini, usu;
public static EditText RFC, solicita;
Button AYUDA;
ImageButton QR, SRFC, QRE;
String corre;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini = findViewById(R.id.tvIni);
        RFC = findViewById(R.id.etRFC);
        QR = findViewById(R.id.btnQR);
        SRFC = findViewById(R.id.btnBuscarRFC);
        QRE = findViewById(R.id.btnFacEmi);
        AYUDA = findViewById(R.id.btnAyuda);
        solicita = findViewById(R.id.etSolicitante);
        usu = findViewById(R.id.tvUsuario);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
    }

    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
    }

        String in;
        in = getIntent().getStringExtra("ini");
        ini.setText(in);

        corre =  getIntent().getStringExtra("correo");


        if(ini.getText().equals("si")){

            String us;
            us = getIntent().getStringExtra("usuario");
            usu.setText(us);
        }else {
            RegresaLogin();

        }




        //botones





SRFC.setOnClickListener(new TextView.OnClickListener() {
    @Override
    public void onClick(View v) {

        final String rfc = RFC.getText().toString();

        // exepciones

        if(RFC.getText().toString().trim().length() == 0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "El campo de nombre no puede permanecer vacio", Toast.LENGTH_SHORT);

            toast1.show();

            return;
        }

        if(solicita.getText().toString().trim().length() == 0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "El campo de nombredel solicitante no puede permanecer vacio", Toast.LENGTH_SHORT);

            toast1.show();

            return;
        }

        if(RFC.getText().toString().trim().length() < 12){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "El RFC no tiene el formato correcto", Toast.LENGTH_SHORT);

            toast1.show();

            return;
        }

        if(RFC.getText().toString().trim().length() > 13){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "El RFC no tiene el formato correcto", Toast.LENGTH_SHORT);

            toast1.show();

            return;
        }
        SRFC.setEnabled(false);

        Response.Listener<String>  response =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonResponse = new JSONObject(response);
                    String success = jsonResponse.getString("success");
                    String solicitantes =  solicita.getText().toString();
                    if(success.equals("true")){
                        SRFC.setEnabled(true);
                        String nombre = jsonResponse.getString("nombre");
                        String situacion = jsonResponse.getString("situacion");

                         insertahistorial(usu.getText().toString(), RFC.getText().toString(), situacion, solicitantes);

                        Intent intent = new Intent(MainActivity.this, RFCMalo.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("rfc",RFC.getText().toString() );
                        intent.putExtra("nombre", nombre );
                        intent.putExtra("situacion",situacion );
                        intent.putExtra("consu",solicita.getText().toString() );
                        intent.putExtra("usuario", usu.getText().toString());
                        intent.putExtra("correo", corre);
                        startActivity(intent);


                    }else{
                        insertahistorial(usu.getText().toString(), RFC.getText().toString(), "Normal", solicitantes);

                        Intent intent = new Intent(MainActivity.this, RFCbueno.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("rfc",RFC.getText().toString() );
                        intent.putExtra("consu",solicita.getText().toString());
                        intent.putExtra("usuario", usu.getText().toString());
                        intent.putExtra("correo", corre);
                        startActivity(intent);

                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        };

        wsBuscaRFC wsbuscarfc = new wsBuscaRFC(rfc, response);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(wsbuscarfc);

    }
});

//boton recibidos
        QR.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QRScann.class));
            }
        });


        //boton emitidos

QRE.setOnClickListener(new TextView.OnClickListener() {

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), qrscannEmi.class));
    }
});

AYUDA.setOnClickListener(new TextView.OnClickListener() {
    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("http://ramirezsoto.com.mx/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
});


    }


    //para ir al inicio de sesion
    private void RegresaLogin() {

        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    // para lectura de xml

    private void lecturaxml(){

        String xmlFile = "your xml file"; // read your xml file to string

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try{

        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlFile));
        Document document = builder.parse(is);
        List<Facturaxml> listarfc = new ArrayList<Facturaxml>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {

            }
        }

    }

      catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void  insertahistorial(String nombre, String rfc, String situacion, String Sol){

             final String nom = nombre;
             final String rfci = rfc;
             final String situ = situacion;
             final String solicitante = Sol;



        Response.Listener<String>  response =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonResponse = new JSONObject(response);
                    String success = jsonResponse.getString("success");

                    if(success.equals("true")){

                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Se registro la busqueda en la base de datos", Toast.LENGTH_SHORT);

                        toast1.show();

                    }else{

                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error al crear el usuario", Toast.LENGTH_SHORT);

                        toast1.show();
                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        };

        wsInserta_historial wsregistro = new wsInserta_historial(nom, rfci, situ, Sol, response);
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        Sol, Toast.LENGTH_SHORT);

        toast1.show();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(wsregistro);

    }

}
