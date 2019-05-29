package com.example.validador;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class wsUsuarioexistente extends StringRequest {

    private  static final String URLLOGIN = "https://cesar3105.000webhostapp.com/Validador/usuariocheck.php?usuario=";
    private Map<String, String > params;
    public wsUsuarioexistente(String usuario, Response.Listener<String> listener){

        super(Method.GET, URLLOGIN + usuario, listener, null );
        params=new HashMap<>();
        params.put("usuario",usuario);


    }


    public Map<String, String>getParams(){
        return params;
    }
}
