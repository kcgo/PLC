package sample.tqi.com.br.planodecarreira.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;

/**
 * Created by alexandre.azevedo on 19/01/2018.
 */

public interface TarefaApi {

    @GET("http://demo5537570.mockable.io/planodecarreira/modulo/listatarefa")
    Observable<List<Tarefa>> getListaTarefa(@Header("Authorization") String authorization);

}
