package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa;

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
 * Created by alexandre.azevedo on 22/01/2018.
 */

public class DetalheTarefaPresenter implements Presenter<DetalheTarefaView> {

    private DetalheTarefaView view;
    private List<Subscription> subscriptionList = new ArrayList<>();

    @Override
    public void attachView(DetalheTarefaView view) {
        this.view = view;
    }

    @Override
    public void dettachView( Context context ) {
        this.view = null;
        for(Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
         }

    }

    public void getTarefaVisaoGeral(final Context context, int idTarefa, int idTalento, int idModulo) {

        final TarefaApi api = ServiceGenerator.create(TarefaApi.class, false, context);

        Subscription subscription = api.getTarefaVisaoGeral("Bearer "+ DataStorage.getAccessToken(),idTalento, idModulo, idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tarefa -> {
                            view.showSuccess(tarefa);
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

    public void getStatusTarefa(final Context context) {

        final TarefaApi api = ServiceGenerator.create(TarefaApi.class, false, context);

        Subscription subscription = api.getTarefaStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tarefa -> {
                            view.showSuccess(tarefa);
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

    public void getLogTarefa(final Context context, int idTarefa, int idTalento, int idModulo) {

        final TarefaApi api = ServiceGenerator.create(TarefaApi.class, false, context);

        Subscription subscription = api.getTarefaLogStatus("Bearer "+ DataStorage.getAccessToken(), idTalento, idModulo, idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(logs -> {
                            view.buildLogList(logs);
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

    public void getComentarioTarefa(final Context context) {

        final TarefaApi api = ServiceGenerator.create(TarefaApi.class, false, context);

        Subscription subscription = api.getTarefaComentario("Bearer "+ DataStorage.getAccessToken(),2,1,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comentarios -> {
                            view.buildComentarioList(comentarios);
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
