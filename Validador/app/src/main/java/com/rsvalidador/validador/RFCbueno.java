package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.validador.R;
import com.rsvalidador.ApiCLass.MyApiAdapter;
import com.rsvalidador.Ob.pdfResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RFCbueno extends AppCompatActivity {
TextView  rfcet, consulto;
Button regresa,  pdf;
    private  Templatepdf templatepdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfcbueno);
regresa = findViewById(R.id.btnRegresar);
        rfcet = findViewById(R.id.etBueno);
        consulto = findViewById(R.id.tvConsulta);
        pdf = findViewById(R.id.btnImprimir);
        String rfc, consul;

        rfc = getIntent().getStringExtra("rfc");
        consul ="Esta validación fue realizada por: " +  getIntent().getStringExtra("consu");
        rfcet.setText(rfc);
        consulto.setText(consul );
        regresa.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                RegresaMenu();
            }
        });

        pdf.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {

                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            /*    templatepdf = new Templatepdf(getApplicationContext());
                templatepdf.Opendocument(getIntent().getStringExtra("rfc"));
                templatepdf.addData(getIntent().getStringExtra("rfc"), "Validacion de rfc", "Laudem S de RL de CV");
                templatepdf.addTitlesrfcbueno("Consultando la relación de contribuyentes con operaciones presuntamente inexistentes el rfc:", getIntent().getStringExtra("rfc"), "No se encontrado en la relación actualizada al 16 de mayo de 2019");
             templatepdf.addParagraph( consulto.getText() + " El dia " + date ) ;
             templatepdf.addImgNameRS();
               // templatepdf.addImgNamelv();
                templatepdf.closedocument();
                templatepdf.viewpdf();

                */

                Call<pdfResponse> call = MyApiAdapter.getApiService().createPDF(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        1
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
        startActivity(intent);


    }


}
