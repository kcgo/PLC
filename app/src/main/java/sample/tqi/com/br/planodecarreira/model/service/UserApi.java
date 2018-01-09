package sample.tqi.com.br.planodecarreira.model.service;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import sample.tqi.com.br.planodecarreira.model.domain.AuthTokenResponse;

public interface UserApi {

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<AuthTokenResponse> login(@Header("Authorization") String authorization,
                                        @Field("username") String username,
                                        @Field("password") String password,
                                        @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("captcha")
    Observable<Response<Void>> validadeCaptcha(@Query("device") String device,
                                               @Field("g-recaptcha-response") String token);
}
