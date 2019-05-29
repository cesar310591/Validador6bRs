package com.example.validador;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class wsRegistro extends StringRequest {

    private  static final String URLLOGIN = "https://cesar3105.000webhostapp.com/Validador/Registrar.php?Nombre_completo=";
    private Map<String, String > params;
    public wsRegistro(String nombre_completo, String usuario, String pass, String correo, String celular, String fechan, Response.Listener<String> listener){
        super(Request.Method.GET, URLLOGIN + nombre_completo +"&usuario=" + usuario +"&pass="+pass + "&correo=" + correo + "&Celular=" + celular+ "&fechan=" + fechan, listener, null );
        params=new HashMap<>();
        params.put("Nombre_completo", nombre_completo);
        params.put("usuario",usuario);
        params.put("pass",pass);
        params.put("correo",correo);
        params.put("Celular",celular);
        params.put("fechan",fechan);
    }


    public Map<String, String>getParams(){
        return params;
    }
}
