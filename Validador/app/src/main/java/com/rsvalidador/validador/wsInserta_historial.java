package com.rsvalidador.validador;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class wsInserta_historial extends StringRequest {

    private  static final String URLLOGIN = "https://cesar3105.000webhostapp.com/Validador/Registrarhistoroal.php?usuario=";
    private Map<String, String > params;
    public wsInserta_historial(String usuario, String rfc,String estado_rfc, String solicitante, Response.Listener<String> listener){

        super(Method.GET, URLLOGIN + usuario +"&rfc=" + rfc + "&estado_rfc=" + estado_rfc + "&solicitante=" + solicitante, listener, null );
        params=new HashMap<>();
        params.put("usuario",usuario);
        params.put("rfc",rfc);
        params.put("estado_rfc",estado_rfc);
        params.put("nombre_solicitante",solicitante);

    }


    public Map<String, String>getParams(){
        return params;
    }
}
