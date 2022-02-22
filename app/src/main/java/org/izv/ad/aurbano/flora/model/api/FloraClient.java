package org.izv.ad.aurbano.flora.model.api;

import org.izv.ad.aurbano.flora.model.entity.CreateResponse;
import org.izv.ad.aurbano.flora.model.entity.Imagen;
import org.izv.ad.aurbano.flora.model.entity.RowsResponse;
import org.izv.ad.aurbano.flora.model.entity.Flora;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface FloraClient {

    @DELETE("api/flora/{id}")
    Call<RowsResponse> deleteFlora(@Path("id") long id);

    @GET("api/flora/{id}")
    Call<Flora> getFlora(@Path("id") long id);

    @GET("api/flora")
    Call<ArrayList<Flora>> getFlora();

    @POST("api/flora")
    Call<CreateResponse> createFlora(@Body Flora flora);

    @PUT("api/flora/{id}")
    Call<RowsResponse> editFlora(@Path("id") long id, @Body Flora flora);

    @Multipart
    @POST("api/imagen/subir")
    Call<Long> subirImagen(@Part MultipartBody.Part file, @Part("idflora") long idFlora, @Part("descripcion") String descripcion);

    @GET("api/imagen/{imagen}") // Obtiene todas las imagenes de la clase Imagen asignada
    Call<ArrayList<Imagen>> getAllImages(@Path("id") long id, @Body Imagen imagen);

    @GET("api/flora/{flora}/imagen") // Obtiene una lista de objetos Imagen
    Call<ArrayList<Imagen>> getImages();

    @GET("api/imagen/{flora}/flora") // Obtiene una imagen de la clase Imagen
    Call<Imagen> getImage(@Path("id") long id, @Body Imagen imagen);
}
