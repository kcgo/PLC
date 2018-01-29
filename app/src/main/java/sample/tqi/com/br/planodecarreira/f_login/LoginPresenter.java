package sample.tqi.com.br.planodecarreira.f_login;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.tqi.com.br.planodecarreira.Presenter;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.AuthTokenResponse;
import sample.tqi.com.br.planodecarreira.model.service.ServiceGenerator;
import sample.tqi.com.br.planodecarreira.model.service.UserApi;
import sample.tqi.com.br.planodecarreira.util.Constants;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

public class LoginPresenter implements Presenter<LoginView> {

    private LoginView view;
    private List<Subscription> subscriptionList = new ArrayList<>();

    @Override
    public void attachView(LoginView view) {
        this.view = view;
    }

    @Override
    public void dettachView( Context context ) {
        this.view = null;
        for(Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
        }

    }


    public void login(final String username, String password, final Context context) {

        final UserApi api = ServiceGenerator.create(UserApi.class, false, context);

        Subscription subscription = api.login(getAuthorization(), username, password, Constants.GRANT_PASSWORD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(( AuthTokenResponse user ) -> {
                            DataStorage.setAccessToken(user.getAccessToken());
                            view.showSuccess();
                        },
                        error -> {
                            if (error instanceof HttpException){
                                view.showError(context.getString(R.string.credentials_invalid));
                            } else {
                                view.showError(context.getString(R.string.unknown_error));
                            }
                        });
        subscriptionList.add(subscription);

    }


    public void validadeCaptcha(final String username, String password, String token, final Context context) {

        final UserApi api = ServiceGenerator.create(UserApi.class, false, context);

        Subscription subscription = api.validadeCaptcha("mobile",token)
                .subscribeOn(Schedulers.io())
                .flatMap(authTokenResponse -> api.login(getAuthorization(), username, password, Constants.GRANT_PASSWORD))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                            view.showSuccess();
                        },
                        error -> {
                            if (error instanceof HttpException){
                                view.showError(context.getString(R.string.credentials_invalid));
                            } else {
                                view.showError(context.getString(R.string.unknown_error));
                            }
                        });
        subscriptionList.add(subscription);

    }

    private String getAuthorization() {
        String base64EncodedCredentials = "Basic Y2xpZW50OnNlY3JldA==";
        return base64EncodedCredentials;
    }

}
