package com.example.validador;

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
TextView ini;
public static EditText RFC, solicita;
Button AYUDA;
ImageButton QR, SRFC, QRE;

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

        if(ini.getText().equals("si")){

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


        Response.Listener<String>  response =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonResponse = new JSONObject(response);
                    String success = jsonResponse.getString("success");

                    if(success.equals("true")){



                        Intent intent = new Intent(MainActivity.this, RFCMalo.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("rfc",RFC.getText().toString() );
                        intent.putExtra("consu",solicita.getText().toString() );
                        startActivity(intent);


                    }else{

                        Intent intent = new Intent(MainActivity.this, RFCbueno.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("rfc",RFC.getText().toString() );
                        intent.putExtra("consu",solicita.getText().toString() );
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

}
