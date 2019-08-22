package com.rsvalidador.ApiCLass;

import com.rsvalidador.Ob.pdfResponse;
import com.rsvalidador.pdfbody.pdfBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApiService {

    @GET("Validador/Reporte")
    Call<pdfResponse> createPDF(
            @Query("fecha") String fecha,
            @Query("correo") String correo,
            @Query("RFC") String rfc,
            @Query("nombre") String nombre,
            @Query("situacion") String situaciom,
            @Query("usuario") String usuario,
            @Query("tipo") Integer tipo,
            @Query("rfcE") String rfcE,
            @Query("rfcR") String rfcR,
            @Query("UUID") String UUID,
            @Query("total") String total
    );


    /*
    @GET("VentaGas")
    Call<pfdResponse> getRfC(
            @Query("ticket") String ticket,
            @Query("monto")double monto
    );
/*
    @GET("Articulos")
    Call<ArticuloClass> getArticulos(
            @Query("Clave") String clave
    );


   */
}
