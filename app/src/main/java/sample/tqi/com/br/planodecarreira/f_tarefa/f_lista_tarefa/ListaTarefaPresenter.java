package sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.tqi.com.br.planodecarreira.Presenter;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.service.ServiceGenerator;
import sample.tqi.com.br.planodecarreira.model.service.TarefaApi;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

/**
 * Created by alexandre.azevedo on 19/01/2018.
 */

public class ListaTarefaPresenter implements Presenter<ListaTarefaView> {

    private ListaTarefaView view;
    private List<Subscription> subscriptionList = new ArrayList<>();

    @Override
    public void attachView(ListaTarefaView view) {
        this.view = view;
    }

    @Override
    public void dettachView( Context context ) {
        this.view = null;
        for(Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
        }
    }

    public void getListaTarefa(final Context context, int idModulo) {

        final TarefaApi api = ServiceGenerator.create(TarefaApi.class, false, context);

        Subscription subscription = api.getListaTarefa("Bearer "+ DataStorage.getAccessToken(), idModulo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tarefas -> {
                            view.buildList(tarefas);
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


