package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

        if(etusuario.getText().toString().trim().length() == 0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "EL campo Usuario no puede permanecer vacio", Toast.LENGTH_SHORT);

            toast1.show();

            return;
        }


        if(etpass.getText().toString().trim().length() == 0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "EL campo contraseña no puede permanecer vacio", Toast.LENGTH_SHORT);

            toast1.show();

            return;
        }


        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Iniciando sesion espere un momento por favor, Esta accion puede tardar unos segundos", Toast.LENGTH_SHORT);

        toast1.show();


     btninicio.setEnabled(false);
        final String nombre = etusuario.getText().toString();
        final String pass = etpass.getText().toString();

        Response.Listener<String>  response =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
try{

    JSONObject jsonResponse = new JSONObject(response);
    String success = jsonResponse.getString("success");
    String correo = jsonResponse.getString("correo");



    if(success.equals("true")){

        buscaestado(correo);
btninicio.setEnabled(true);

    }else{

        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Usuario no encontrado", Toast.LENGTH_SHORT);

        toast1.show();
    }
}

 catch (JSONException e) {
     btninicio.setEnabled(true);
    e.printStackTrace();
}

            }


        };
        btninicio.setEnabled(true);
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

    public void buscaestado(final String correo){

        final String usuario = etusuario.getText().toString();


        Response.Listener<String>  response =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonResponse = new JSONObject(response);
                    String success = jsonResponse.getString("success");
                    String est = jsonResponse.getString("estado");
                    String corr = correo;

                    if(success.equals("true")){

                        if(est.equals("Normal")) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("ini", "si");
                            intent.putExtra("usuario", etusuario.getText().toString());
                            intent.putExtra("correo", corr);
                         //guardamos en cache el usuario si es que es valido
                            SharedPreferences myPreferences
                                    = PreferenceManager.getDefaultSharedPreferences(Login.this);


                            SharedPreferences.Editor myEditor = myPreferences.edit();
                            myEditor.putString("usuario", etusuario.getText().toString());
                            myEditor.putString("correo", corr);
                            myEditor.putString("ini", "si");


                            myEditor.commit();

                            startActivity(intent);
                        }else{

                            Toast toast2 =
                                    Toast.makeText(getApplicationContext(),
                                            "El usuario se encuentra bloqueado por pago pendiente", Toast.LENGTH_SHORT);

                            toast2.show();

                        }
                    }else{

                        Toast toast2 =
                                Toast.makeText(getApplicationContext(),
                                        "Usuario no encontrado", Toast.LENGTH_SHORT);

                        toast2.show();
                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        };

        wsEstado wsestado = new wsEstado(usuario, response);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(wsestado);

    }

}
