package sample.tqi.com.br.planodecarreira.model.service;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;

/**
 * Created by katia.goncalves on 10/01/2018.
 */

public interface PerfilAcessoApi {

    @GET("service/permissions")
    Observable<List<PerfilAcesso>> getPerfilAcesso(@Header ("Authorization") String authorization);


}
