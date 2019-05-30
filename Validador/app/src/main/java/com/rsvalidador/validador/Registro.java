package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.validador.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {
Button registrar;
EditText nombre, usuario, pass, correo, tel, fechan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registrar = findViewById(R.id.btnRegistroR);
        nombre = findViewById(R.id.etNombreR);
        usuario = findViewById(R.id.etUsuarioR);
        pass = findViewById(R.id.etPassR);
        correo = findViewById(R.id.etCorreoR);
        tel = findViewById(R.id.etTelR);
        fechan = findViewById(R.id.etFechaR);

        registrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String nombrer = nombre.getText().toString();
                final String usuarior = usuario.getText().toString();
                final String passr = pass.getText().toString();
                final String correor = correo.getText().toString();
                final String telr = tel.getText().toString();
                final String fechanr = fechan.getText().toString();

          //checamos  que no tenga valores en blanco

                if(nombre.getText().toString().trim().length() == 0){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de nombre no puede permanecer vacio", Toast.LENGTH_SHORT);

                    toast1.show();

                    return;
                }

                if(usuario.getText().toString().trim().length() == 0){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de usuario no puede permanecer vacio", Toast.LENGTH_SHORT);

                    toast1.show();

                    return;
                }

                if(pass.getText().toString().trim().length() == 0){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de password no puede permanecer vacio", Toast.LENGTH_SHORT);

                    toast1.show();

                    return;
                }

                if(correo.getText().toString().trim().length() == 0){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de correo no puede permanecer vacio", Toast.LENGTH_SHORT);

                    toast1.show();

                    return;
                }

                if(tel.getText().toString().trim().length() == 0){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de telefono no puede permanecer vacio", Toast.LENGTH_SHORT);

                    toast1.show();

                    return;
                }

                if(fechan.getText().toString().trim().length() == 0){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "el campo fecha de nacimiento no puede permanecer vacio", Toast.LENGTH_SHORT);

                    toast1.show();

                    return;
                }

                //checamos el formato de la fecha que debe ser anio/mes/dia

                if(fechan.getText().toString().charAt(2) == '-'){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de fecha nacimiento no tiene el formato correcto debe ser año-mes-dia (1991-05-31)", Toast.LENGTH_SHORT);

                    toast1.show();
                    return;
                }

                if(fechan.getText().toString().charAt(2) == '/'){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de fecha nacimiento no tiene el formato correcto debe ser año-mes-dia (1991-05-31)", Toast.LENGTH_SHORT);

                    toast1.show();
                    return;
                }

                if(fechan.getText().toString().charAt(5) == '/'){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de fecha nacimiento no tiene el formato correcto debe ser año-mes-dia (1991-05-31)", Toast.LENGTH_SHORT);

                    toast1.show();
                    return;
                }

                if(fechan.getText().toString().charAt(5) == '/'){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de fecha nacimiento no tiene el formato correcto debe ser año-mes-dia (1991-05-31)", Toast.LENGTH_SHORT);

                    toast1.show();
                    return;
                }

                int c = 0;
                for (int x=0;x<fechan.getText().toString().length();x++){
                    if(fechan.getText().toString().charAt(x) == '-') {
                        c ++;
                    }

                    if(fechan.getText().toString().charAt(x) == '/') {
                        c ++;
                    }

                    }

                if(c>2){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de fecha nacimiento no tiene el formato correcto debe ser año-mes-dia (1991-05-31)", Toast.LENGTH_SHORT);

                    toast1.show();
                    return;
                }

                if(c<2){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "El campo de fecha nacimiento no tiene el formato correcto debe ser año-mes-dia (1991-05-31)", Toast.LENGTH_SHORT);

                    toast1.show();
                    return;
                }


                if(fechan.getText().toString().trim().length() != 10){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "el campo fecha de nacimiento no puede permanecer vacio", Toast.LENGTH_SHORT);

                    toast1.show();

                    return;
                }




          registrar.setEnabled(false);

                Response.Listener<String>  response =new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{

                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");

                            if(success.equals("true")){

                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "El Usuario ya existe, pruebe con otro nombre de usuario", Toast.LENGTH_SHORT);

                                toast1.show();
                                registrar.setEnabled(true);
return;
                            }else{

                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Creando el usuario espere un momento por favor", Toast.LENGTH_SHORT);

                                toast1.show();

                                insertausuario();
                            }
                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                };

                wsUsuarioexistente wsusuarioexistente = new  wsUsuarioexistente(usuarior, response);
                RequestQueue queue = Volley.newRequestQueue(Registro.this);
                queue.add(wsusuarioexistente);
            }
        });



    }


    public void insertausuario(){
        final String nombrer = nombre.getText().toString();
        final String usuarior = usuario.getText().toString();
        final String passr = pass.getText().toString();
        final String correor = correo.getText().toString();
        final String telr = tel.getText().toString();
        final String fechanr = fechan.getText().toString();

        Response.Listener<String>  response =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonResponse = new JSONObject(response);
                    String success = jsonResponse.getString("success");

                    if(success.equals("true")){

                        registrar.setEnabled(true);
                        Intent intent = new Intent(Registro.this, Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }else{

                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Error al crear el usuario", Toast.LENGTH_SHORT);

                        toast1.show();
                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        };

        wsRegistro wsregistro = new wsRegistro(nombrer, usuarior, passr, correor, telr, fechanr, response);
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(wsregistro);



    }
}
