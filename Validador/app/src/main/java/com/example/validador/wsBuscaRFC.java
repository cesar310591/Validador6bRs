package com.example.validador;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class wsBuscaRFC extends StringRequest {

    private  static final String URLLOGIN = "https://cesar3105.000webhostapp.com/Validador/BUSCARFCN.php?rfc=";
    private Map<String, String > params;
    public wsBuscaRFC(String rfc, Response.Listener<String> listener){

        super(Method.GET, URLLOGIN + rfc, listener, null );
        params=new HashMap<>();
        params.put("rfc",rfc);

    }


    public Map<String, String>getParams(){
        return params;
    }
}
