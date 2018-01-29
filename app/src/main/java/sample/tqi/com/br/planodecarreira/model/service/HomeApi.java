package sample.tqi.com.br.planodecarreira.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.Modulo;
import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;

/**
 * Created by alexandre.azevedo on 18/01/2018.
 */

public interface HomeApi {

    @GET("avaliacao/modulos?filtro=vigente")
    Observable<Modulo> getModuloVigente(@Header("Authorization") String authorization);

    @GET("avaliacao/modulos?filtro=concluidos")
    Observable<List<Modulo>> getHistorico(@Header("Authorization") String authorization);
}
