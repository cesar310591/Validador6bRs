package com.example.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RFCMalo extends AppCompatActivity {
TextView rfcet, consulto;
Button regresam,  pdf;
    private  Templatepdf templatepdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfcmalo);
        consulto = findViewById(R.id.tvConsultam);
regresam = findViewById(R.id.btnRegresaM);
        rfcet = findViewById(R.id.etMalo);
        pdf = findViewById(R.id.btnImprimir);
        String rfc, consul;
        rfc = getIntent().getStringExtra("rfc");
        consul =  getIntent().getStringExtra("consu");

        rfcet.setText(rfc);
        consulto.setText(consul );

  rfcet.setText(rfc );

  regresam.setOnClickListener(new TextView.OnClickListener() {

      @Override
      public void onClick(View v) {
          RegresaMenu();
      }
  });

        pdf.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {

                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                templatepdf = new Templatepdf(getApplicationContext());
                templatepdf.Opendocument(getIntent().getStringExtra("rfc"));
                templatepdf.addData(getIntent().getStringExtra("rfc"), "Validacion de rfc", "Laudem S de RL de CV");
                templatepdf.addTitlesrfcmalo("Consultando la relación de contribuyentes con operaciones presuntamente inexistentes el rfc:", getIntent().getStringExtra("rfc"), "Se encuentra con irregularidades y cualquier interaccion podria tener consecuencias con las autoridades");
                templatepdf.addParagraph("Validacion solicitada por: " + consulto.getText() + " El dia " + date ) ;
                templatepdf.addImgNameRS();
             //   templatepdf.addImgNamelv();
                templatepdf.closedocument();
                templatepdf.viewpdf();
            }
        });

    }




    private void RegresaMenu() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ini","si" );
        startActivity(intent);


    }


}
