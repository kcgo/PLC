package sample.tqi.com.br.planodecarreira.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.Feedback;
import sample.tqi.com.br.planodecarreira.model.domain.Modulo;

/**
 * Created by katia.goncalves on 22/01/2018.
 */

public interface ModuloApi {
    @GET("http://10.10.0.46:8080/avaliacao/modulos?filtro=concluidos")
    public Observable<Modulo> getModulo( @Header("Authorization") String authorization );

    @GET("avaliacao/{idTalento}/{idModulo}/feedbacks")
    Observable<List<Feedback>> getFeedback(@Header("Authorization") String authorization,
                                           @Path("idTalento") int idTalento,
                                           @Path("idModulo") int idModulo);
}

