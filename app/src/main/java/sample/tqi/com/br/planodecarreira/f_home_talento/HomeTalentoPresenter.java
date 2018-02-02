package sample.tqi.com.br.planodecarreira.f_home_talento;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.tqi.com.br.planodecarreira.Presenter;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.service.HomeApi;
import sample.tqi.com.br.planodecarreira.model.service.ServiceGenerator;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

/**
 * Created by alexandre.azevedo on 18/01/2018.
 */

public class HomeTalentoPresenter implements Presenter<HomeTalentoView> {

    private HomeTalentoView view;
    private List<Subscription> subscriptionList = new ArrayList<>();


    @Override
    public void attachView( HomeTalentoView view ) {
        this.view= view;
    }

    @Override
    public void dettachView( Context context ) {
        this.view = null;
        for(Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
        }
    }

    public void getModuloVigente(final Context context) {

        final HomeApi api = ServiceGenerator.create(HomeApi.class, false, context);

        Subscription subscription = api.getModuloVigente("Bearer "+ DataStorage.getAccessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modulo -> {
                            view.showSuccess(modulo);
                        },
                        error -> {
                            if (error instanceof HttpException){
                                view.showError(error.getMessage().toString());
                            } else {
                                view.showError(context.getString(R.string.unknown_error));
                            }
                        });
        subscriptionList.add(subscription);
    }

    public void getHistorico(final Context context) {

        final HomeApi api = ServiceGenerator.create(HomeApi.class, false, context);

        Subscription subscription = api.getHistorico("Bearer "+ DataStorage.getAccessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modulos -> {
                            view.buildList(modulos);
                        },
                        error -> {
                            if (error instanceof HttpException){
                                view.showError(error.getMessage().toString());
                            } else {
                                view.showError(context.getString(R.string.unknown_error));
                            }
                        });
        subscriptionList.add(subscription);
    }

    public void saveTypeAccess(String tipoAcesso){
        DataStorage.setTypeAccess(tipoAcesso);
    }
}