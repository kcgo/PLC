package sample.tqi.com.br.planodecarreira.f_perfil_acesso;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_login.LoginView;
import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

public class PerfilAcessoActivity extends Activity implements PerfilAcessoView {

    private PerfilAcessoPresenter presenter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_perfil_acesso );

        presenter = new PerfilAcessoPresenter();
        presenter.attachView( this );

        presenter.getPerfilAcesso( PerfilAcessoActivity.this );

    }


    @Override
    public void showError( String string ) {
        Context context = getApplicationContext();
        CharSequence text = " ";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText( context, text, duration );
        toast.show();
    }


    @Override
    public void buildAdapter( List <PerfilAcesso> lista ) {
        RecyclerView recyclerView = (RecyclerView) findViewById( R.id.rv_perfil );
        recyclerView.setAdapter( new RecyclerAdapter( this, (lista ) ));
        RecyclerView.LayoutManager layout = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
        recyclerView.setLayoutManager( layout );
    }
}