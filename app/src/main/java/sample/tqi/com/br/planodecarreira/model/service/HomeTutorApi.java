package sample.tqi.com.br.planodecarreira.model.service;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.ListaTalentos;

/**
 * Created by katia.goncalves on 17/01/2018.
 */

public interface HomeTutorApi {
    //@GET("home/tutor")
    @GET("http://demo5537570.mockable.io/planodecarreira/hometutor/listatalento")
    Observable<List<ListaTalentos>> getHomeTutor(  );
    //Observable<List<HomeTutor>> getHomeTutor( @Header("Authorization") String authorization );
}
