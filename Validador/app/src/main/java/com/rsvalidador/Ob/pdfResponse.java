package com.rsvalidador.Ob;

import java.io.Serializable;

public class pdfResponse implements Serializable {

   private String RFC;
   private String nombre;

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String rfc) {
        this.RFC = rfc;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }



}
