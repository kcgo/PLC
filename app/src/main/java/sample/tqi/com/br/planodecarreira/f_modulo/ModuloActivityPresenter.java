package sample.tqi.com.br.planodecarreira.f_modulo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.tqi.com.br.planodecarreira.Presenter;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.service.ModuloApi;
import sample.tqi.com.br.planodecarreira.model.service.ServiceGenerator;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

/**
 * Created by katia.goncalves on 22/01/2018.
 */

public class ModuloActivityPresenter implements Presenter<ModuloView> {

    private ModuloView view;
    private List <Subscription> subscriptionList = new ArrayList <>();

    @Override
    public void attachView( ModuloView view ) {
        this.view = view;
    }

    @Override
    public void dettachView( Context context ) {
        this.view = null;
        for (Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
        }

    }



    public void getFeedback(final Context context, int idTalento, int idModulo) {

        final ModuloApi api = ServiceGenerator.create(ModuloApi.class, false, context);

        Subscription subscription = api.getFeedback("Bearer "+ DataStorage.getAccessToken(), idTalento, idModulo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modulos -> {
                            view.buildFeedBackList(modulos);
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
}


