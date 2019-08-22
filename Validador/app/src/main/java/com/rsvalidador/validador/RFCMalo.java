package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.validador.R;
import com.rsvalidador.ApiCLass.MyApiAdapter;
import com.rsvalidador.Ob.pdfResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RFCMalo extends AppCompatActivity {
TextView rfcet, consulto, mal;
Button regresam,  pdf;
    String rfc, consul, nombre, situacion, tex;
    private  Templatepdf templatepdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfcmalo);
        consulto = findViewById(R.id.tvConsultam);
        regresam = findViewById(R.id.btnRegresaM);
        rfcet = findViewById(R.id.etMalo);
        mal = findViewById(R.id.tvMalo);
        pdf = findViewById(R.id.btnImprimir);


        rfc = getIntent().getStringExtra("rfc");
        nombre = getIntent().getStringExtra("nombre");
        situacion = getIntent().getStringExtra("situacion");
        tex = "Se ha encontrado en la relación de las personas físicas y morales que se ubican en el supuesto del artículo 69-B del Código Fiscal de la Federación con el nombre de la empresa: " + nombre + " Y la situacion: " + situacion +".";
        consul = "Esta validación fue realizada por: " + getIntent().getStringExtra("consu");

        rfcet.setText(rfc);
        consulto.setText(consul );
        mal.setText(tex);
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

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "El resultado de la validación se enviara al correo electronico registrado con el usuario este proceso puede tardar unos minutos.", Toast.LENGTH_SHORT);

                toast1.show();



                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
              /*  templatepdf = new Templatepdf(getApplicationContext());
                templatepdf.Opendocument(getIntent().getStringExtra("rfc"));
                templatepdf.addData(getIntent().getStringExtra("rfc"), "Validacion de rfc", "Laudem S de RL de CV");
                templatepdf.addTitlesrfcmalo("Consultando la relación de contribuyentes con operaciones presuntamente inexistentes el rfc:", getIntent().getStringExtra("rfc"), tex);
                templatepdf.addParagraph("Validacion solicitada por: " + consulto.getText() + " El dia " + date ) ;
                templatepdf.addImgNameRS();
             //   templatepdf.addImgNamelv();
                templatepdf.closedocument();
                templatepdf.viewpdf();

                */
                Call<pdfResponse> call = MyApiAdapter.getApiService().createPDF(
                        date,
                        getIntent().getStringExtra("correo"),
                        rfc,
                        nombre,
                        situacion,
                        getIntent().getStringExtra("consu") ,
                        2,
                        getIntent().getStringExtra("rfc_e") ,
                        getIntent().getStringExtra("rfc_r") ,
                        getIntent().getStringExtra("uuid") ,
                        getIntent().getStringExtra("total")
                );
                call.enqueue(new Callback<pdfResponse>() {

                    @Override
                    public void onResponse(Call<pdfResponse> call, Response<pdfResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<pdfResponse> call, Throwable t) {

                    }
                });

            }
        });

    }




    private void RegresaMenu() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ini","si" );
        intent.putExtra("usuario", getIntent().getStringExtra("usuario"));
        intent.putExtra("correo", getIntent().getStringExtra("correo"));
        startActivity(intent);


    }


}
