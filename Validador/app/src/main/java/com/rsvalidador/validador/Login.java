package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.validador.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText etusuario, etpass;
    ImageButton btninicio;
   Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etusuario = findViewById(R.id.etUsuario);
        etpass = findViewById(R.id.etPass);
        btninicio = findViewById(R.id.ibtnEnviar);
        registrar = findViewById(R.id.btnResgistrar);

        //boton iniciar sesion

btninicio.setOnClickListener(new View.OnClickListener(){

    @Override
    public void onClick(View v) {
        final String nombre = etusuario.getText().toString();
        final String pass = etpass.getText().toString();

        Response.Listener<String>  response =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
try{

    JSONObject jsonResponse = new JSONObject(response);
    String success = jsonResponse.getString("success");

    if(success.equals("true")){


        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ini","si" );
        startActivity(intent);

    }else{

        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Usuario no encontrado", Toast.LENGTH_SHORT);

        toast1.show();
    }
}

 catch (JSONException e) {
    e.printStackTrace();
}

            }


        };

        wsLogin wslogin = new wsLogin(nombre, pass, response);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(wslogin);
    }
});


       // boton registrar

        registrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }
}
