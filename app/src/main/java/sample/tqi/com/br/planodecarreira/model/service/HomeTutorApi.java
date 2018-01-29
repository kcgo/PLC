package sample.tqi.com.br.planodecarreira.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.Talento;

/**
 * Created by katia.goncalves on 17/01/2018.
 */

public interface HomeTutorApi {
    //@GET("home/tutor")
    @GET("http://10.10.0.46:8080/avaliacao/talentos")
    Observable<List<Talento>> getHomeTutor(@Header("Authorization") String authorization, @Query( "filtro") String filtro );

    //Observable<List<HomeTutor>> getHomeTutor( @Header("Authorization") String authorization );

}
