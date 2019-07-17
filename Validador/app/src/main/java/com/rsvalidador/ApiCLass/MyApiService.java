package com.rsvalidador.ApiCLass;

import com.rsvalidador.Ob.pdfResponse;
import com.rsvalidador.pdfbody.pdfBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApiService {

    @POST("Facturacion/Facturas")
    Call<pdfResponse> createPDF(
            @Body pdfBody pdfBody
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
