package com.rsvalidador.validador;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class wsRegistraFactura extends StringRequest {

    private static final String URLLOGIN = "https://cesar3105.000webhostapp.com/Validador/facturavalidada.php?usuario=";
    private Map<String, String> params;

    public wsRegistraFactura(String usuario, String rfce, String rfcr, String uuid, String total, Response.Listener<String> listener) {

        super(Method.GET, URLLOGIN + usuario + "&rfce=" + rfce + "&rfcr=" + rfcr + "&uuid=" + uuid + "&total=" + total, listener, null);
        params = new HashMap<>();
        params.put("rfce", rfce);
        params.put("rfcr", rfcr);
        params.put("uuid", uuid);
        params.put("total", total);

    }


    public Map<String, String> getParams() {
        return params;
    }
}
