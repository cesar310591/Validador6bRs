package com.example.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

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

        for (int x=0;x<cadena.length();x++){
            if(cadena.charAt(x) == '&'){
                if(cadena.charAt(x+1) == 'r'){
                    if(cadena.charAt(x+2) == 'r'){
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




        MainActivity.RFC.setText(RFCE);
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