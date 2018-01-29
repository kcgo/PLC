package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_status;

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
import sample.tqi.com.br.planodecarreira.model.service.ServiceGenerator;
import sample.tqi.com.br.planodecarreira.model.service.TarefaApi;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

/**
 * Created by katia.goncalves on 29/01/2018.
 */

public class StatusTarefaPresenter implements Presenter<StatusTarefaView> {
    private StatusTarefaView view;
    private List <Subscription> subscriptionList = new ArrayList <>();


    @Override
    public void attachView( StatusTarefaView view ) {
        this.view = view;

    }
    @Override
    public void dettachView( Context context ) {
        this.view = null;
        for (Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
        }
    }

    public void postComentario( final Context context, Comentario comentario ) {

        final TarefaApi api = ServiceGenerator.create( TarefaApi.class, false, context );

        Subscription subscription = api.postComentario( "Bearer " + DataStorage.getAccessToken(),comentario,2,1,1)
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( stringResponse -> {
                            view.showSuccess( null );
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


}