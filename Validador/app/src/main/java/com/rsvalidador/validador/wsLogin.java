package com.rsvalidador.validador;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class wsLogin extends  StringRequest{

     private  static final String URLLOGIN = "https://cesar3105.000webhostapp.com/Validador/Login.php?usuario=";
     private Map<String, String >params;
            public wsLogin(String usuario, String pass, Response.Listener<String> listener){

                super(Method.GET, URLLOGIN + usuario +"&pass=" + pass, listener, null );
                params=new HashMap<>();
                params.put("usuario",usuario);
                params.put("pass",pass);

}


public Map<String, String>getParams(){
                return params;
}
}
