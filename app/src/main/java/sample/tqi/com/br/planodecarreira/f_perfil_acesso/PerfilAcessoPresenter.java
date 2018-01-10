package sample.tqi.com.br.planodecarreira.f_perfil_acesso;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Completable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.tqi.com.br.planodecarreira.Presenter;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.AuthTokenResponse;
import sample.tqi.com.br.planodecarreira.model.service.PerfilAcessoApi;
import sample.tqi.com.br.planodecarreira.model.service.ServiceGenerator;
import sample.tqi.com.br.planodecarreira.model.service.UserApi;
import sample.tqi.com.br.planodecarreira.util.Constants;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

import static rx.Observable.subscribe;

/**
 * Created by katia.goncalves on 10/01/2018.
 */

public class PerfilAcessoPresenter implements Presenter<PerfilAcessoView> {

    private PerfilAcessoView view;
    private List <Subscription> subscriptionList = new ArrayList <>();

    @Override
    public void attachView( PerfilAcessoView view ) {
        this.view = view;
    }

    @Override
    public void dettachView( Context context ) {
        this.view = null;
        for (Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
        }
    }
    public void getPerfilAcesso( final Context context) {
        PerfilAcessoApi api = ServiceGenerator.create(PerfilAcessoApi.class, true,context);

        Subscription subscription = (Subscription) api.getPerfilAcesso(DataStorage.getAccessToken());
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(( listaPerfil ) -> {
                    buildAdapter(listaPerfil);
                        error -> {
                            if (error instanceof HttpException){
                                view.showError(context.getString(R.string.credentials_invalid));
                            } else {
                                view.showError(context.getString(R.string.unknown_error));
                            }
                        });
        subscriptionList.add(subscription);

    }


    }


}


