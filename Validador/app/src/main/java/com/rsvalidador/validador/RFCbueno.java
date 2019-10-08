package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.validador.R;
import com.rsvalidador.ApiCLass.MyApiAdapter;
import com.rsvalidador.Ob.pdfResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RFCbueno extends AppCompatActivity {
TextView  rfcet, consulto, consulto2;
Button regresa,  pdf;
    private  Templatepdf templatepdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfcbueno);
regresa = findViewById(R.id.btnRegresar);
        rfcet = findViewById(R.id.etBueno);
        consulto = findViewById(R.id.tvConsulta);
        consulto2 = findViewById(R.id.tvConsulta2);
        pdf = findViewById(R.id.btnImprimir);
        String rfc, consul;

        rfc = getIntent().getStringExtra("rfc");
        consul ="Esta validación fue realizada por: " +  getIntent().getStringExtra("consu");

      //sacamos la fecha de la actualizacion de la lista

      buscaactualizacion();




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
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "El resultado de la validación se enviara al correo electronico registrado con el usuario este proceso puede tardar unos minutos.", Toast.LENGTH_SHORT);

                toast1.show();


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
                        date,
                        getIntent().getStringExtra("correo"),
                        rfcet.getText().toString(),
                        "",
                        "Normal",
                        getIntent().getStringExtra("consu") ,
                        1,
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

    private void buscaactualizacion() {
       String fecha = "";

        com.android.volley.Response.Listener<String>  response =new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonResponse = new JSONObject(response);
                    String success = jsonResponse.getString("success");

                    if(success.equals("true")){

                    String fecha = jsonResponse.getString("actualizacion");

                        consulto2.setText("Fecha de actualizacion: " + fecha);

                    }else{


                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        };

        wsverificaactualizacion wsverificaactualizacion = new wsverificaactualizacion( response);
        RequestQueue queue = Volley.newRequestQueue(RFCbueno.this);
        queue.add(wsverificaactualizacion);


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
