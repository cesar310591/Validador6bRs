package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.util.Log.println;

public class qrscannEmi extends AppCompatActivity implements ZXingScannerView.ResultHandler {

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
        String uuid = "", rfc_e = "", rfc_r = "", total = "" ,palabratam3;



        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        cadena, Toast.LENGTH_SHORT);

        toast1.show();

// saca el rfc que se busca en el listado
        for (int x=0;x<cadena.length();x++){

         //saca el rfc RR
            if(cadena.charAt(x) == 'r'){
                if(cadena.charAt(x+1) == 'r'){
                    if(cadena.charAt(x+2) == '='){
                        RFCE = cadena.substring(x+3);

                        for (int y=0;y<RFCE.length();y++){
                            if(RFCE.charAt(y) == '&'){
                                RFCE = RFCE.substring(0, y);
                                rfc_r =  RFCE;
                            }
                        }

                    }


                }

            }

// sacamos el uuid

            if(cadena.charAt(x) == 'i'){
                if(cadena.charAt(x+1) == 'd'){
                    if(cadena.charAt(x+2) == '='){
                        uuid = cadena.substring(x+3);

                        for (int y=0;y<uuid.length();y++){
                            if(uuid.charAt(y) == '&'){
                                uuid = uuid.substring(0, y);
                            }
                        }

                    }


                }

            }
// sacamos el rfc e

            if(cadena.charAt(x) == 'r'){
                if(cadena.charAt(x+1) == 'e'){
                    if(cadena.charAt(x+2) == '='){
                        rfc_e = cadena.substring(x+3);

                        for (int y=0;y<rfc_e.length();y++){
                            if(rfc_e.charAt(y) == '&'){
                                rfc_e = rfc_e.substring(0, y);
                            }
                        }

                    }


                }

            }

            // sacamos el total

            if(cadena.charAt(x) == 't'){
                if(cadena.charAt(x+1) == 't'){
                    if(cadena.charAt(x+2) == '='){
                        total = cadena.substring(x+3);

                        for (int y=0;y<total.length();y++){
                            if(total.charAt(y) == '&'){
                                total = total.substring(0, y);
                            }
                        }

                    }


                }

            }


        }



//sacamos los demas datos para validar la factura

/*

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

*/






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