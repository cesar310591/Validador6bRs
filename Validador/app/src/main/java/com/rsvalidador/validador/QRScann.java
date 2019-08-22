package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScann extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scanerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanerview = new ZXingScannerView(this);
        setContentView(scanerview);
    }

    @Override
    public void handleResult(Result result) {

        String cadena = result.getText();
String RFCE = "";
        String RFCR;
        String uuid = "", rfc_e = "", rfc_r = "", total = "" ,palabratam3;
        for (int x=0;x<cadena.length();x++){
           if(cadena.charAt(x) == '&'){
               if(cadena.charAt(x+1) == 'r'){
                   if(cadena.charAt(x+2) == 'e'){
                       RFCE = cadena.substring(x+4);

                       for (int y=0;y<RFCE.length();y++){
                           if(RFCE.charAt(y) == '&'){
                               RFCE = RFCE.substring(0, y);
                           }
                       }

                   }


               }

           }
        }



//sacamos los demas datos para validar la factura



        String[] palabras = cadena.split("&");

        for (String palabra : palabras)
        {
            //vemos que cadena es y dependiendo de que sea lo guardamos en la variable correspondiente
            palabratam3 = palabra.substring(0,2);

            //los casos de las cadenas
            if (palabratam3.equals("id")){

                uuid = palabra.substring(3, palabra.length());

            }

            if (palabratam3.equals("re")){

                rfc_e = palabra.substring(3, palabra.length());

            }

            if (palabratam3.equals("rr")){

                rfc_r = palabra.substring(3, palabra.length());

            }

            if (palabratam3.equals("tt")){

                total = palabra.substring(3, palabra.length());

            }



        }

        MainActivity.RFC.setText(RFCE);
        MainActivity.rfc_em.setText(rfc_e);
        MainActivity.rfc_rr.setText(rfc_r);
        MainActivity.uuid.setText(uuid);
        MainActivity.total.setText(total);
onBackPressed();
    }


    @Override
    public void onPause(){
        super.onPause();
        scanerview.stopCamera();
    }

    @Override
    public void onResume(){
        super.onResume();
        scanerview.setResultHandler(this);
        scanerview.startCamera();
    }
}
