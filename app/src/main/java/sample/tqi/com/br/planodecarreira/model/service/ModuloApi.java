package sample.tqi.com.br.planodecarreira.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.Modulo;

/**
 * Created by katia.goncalves on 22/01/2018.
 */

public class ModuloApi {
    @GET("http://demo5537570.mockable.io/planodecarreira/hometalento/modulovigente")
    public Observable<Modulo> getModulo( @Header("Authorization") String authorization );

    @GET("https://demo5537570.mockable.io/planodecarreira/hometalento/historico")
    Observable<List<Modulo>> getFeedback( @Header("Authorization") String authorization);
}

