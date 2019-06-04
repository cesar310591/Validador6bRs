package com.rsvalidador.validador;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class wsEstado extends StringRequest {

    private  static final String URLLOGIN = "https://cesar3105.000webhostapp.com/Validador/consultaestado.php?usuario=";
    private Map<String, String > params;
    public wsEstado(String usuario, Response.Listener<String> listener){

        super(Method.GET, URLLOGIN + usuario, listener, null );
        params=new HashMap<>();
        params.put("usuario",usuario);
    }


    public Map<String, String>getParams(){
        return params;
    }
}
