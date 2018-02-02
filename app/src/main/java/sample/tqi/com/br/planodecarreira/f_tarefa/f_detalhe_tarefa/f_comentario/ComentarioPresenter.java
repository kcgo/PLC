package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.tqi.com.br.planodecarreira.Presenter;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.Comentario;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;
import sample.tqi.com.br.planodecarreira.model.service.ServiceGenerator;
import sample.tqi.com.br.planodecarreira.model.service.TarefaApi;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

/**
 * Created by katia.goncalves on 30/01/2018.
 */

public class ComentarioPresenter implements Presenter<ComentarioView> {
    private ComentarioView view;
    private List<Subscription> subscriptionList = new ArrayList<>();

    @Override
    public void attachView( ComentarioView view ) {
        this.view = view;
    }

    @Override
    public void dettachView( Context context ) {
        this.view= null;
        for(Subscription subscription :subscriptionList){
            subscription.unsubscribe();
        }

    }
    public void postComentario( final Context context, Comentario comentario, Tarefa tarefa ) {

        final TarefaApi api = ServiceGenerator.createWithContentType( TarefaApi.class, false, context );

        Subscription subscription = api.postComentario( "Bearer " + DataStorage.getAccessToken(), comentario, tarefa.getId_talento().intValue(), tarefa.getId_modulo().intValue(), tarefa.getId_tarefa().intValue() )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( stringResponse -> {
                            view.showSuccess( stringResponse );
                        },
                        error -> {
                            if (error instanceof HttpException) {
                                view.showError( error.getMessage().toString() );
                            } else {
                                view.showError( context.getString( R.string.unknown_error ) );
                            }
                        } );
        subscriptionList.add( subscription );
    }

    public void getComentarioTarefa(Context context, int idTalento,int idModulo,int idTarefa) {

        final TarefaApi api = ServiceGenerator.create(TarefaApi.class, false, context);

        Subscription subscription = api.getTarefaComentario("Bearer "+ DataStorage.getAccessToken(),idTalento,idModulo,idTarefa)
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