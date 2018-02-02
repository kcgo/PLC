package sample.tqi.com.br.planodecarreira.f_Tutor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sample.tqi.com.br.planodecarreira.Presenter;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.service.HomeTutorApi;
import sample.tqi.com.br.planodecarreira.model.service.ServiceGenerator;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

/**
 * Created by katia.goncalves on 17/01/2018.
 */

public class HomeTutorPresenter implements Presenter<HomeTutorView> {

    private HomeTutorView view;
    private List <Subscription> subscriptionList = new ArrayList <>();

    @Override
    public void attachView( HomeTutorView view ) {
        this.view = view;
    }

    @Override
    public void dettachView( Context context ) {
        this.view = null;
        for (Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
        }

    }

    public void getHomeTutor( final Context context, String estado ) {
        HomeTutorApi api = ServiceGenerator.create( HomeTutorApi.class, false, context );

        Subscription subscription = api.getHomeTutor( "Bearer " + DataStorage.getAccessToken(), estado )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( listaTalentos -> {
                            view.buildAdapter( listaTalentos );
                        },
                        error -> {
                            if (error instanceof HttpException) {
                                view.showError( context.getString( R.string.credentials_invalid ) );
                            } else {
                                view.showError( context.getString( R.string.unknown_error ) );
                            }
                        } );
        subscriptionList.add( subscription );
    }

    public void saveTypeAccess(String tipoAcesso){
        DataStorage.setTypeAccess(tipoAcesso);
    }
}