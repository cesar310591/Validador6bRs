package com.rsvalidador.validador;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class wsverificaactualizacion extends StringRequest {

    private  static final String URLLOGIN = "https://cesar3105.000webhostapp.com/Validador/buscaActualizacion.php";
    private Map<String, String > params;
    public wsverificaactualizacion( Response.Listener<String> listener){

        super(Method.GET, URLLOGIN , listener, null );
        params=new HashMap<>();

    }


    public Map<String, String>getParams(){
        return params;
    }
}
