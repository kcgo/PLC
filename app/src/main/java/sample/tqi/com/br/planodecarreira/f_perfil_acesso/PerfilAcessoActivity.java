package sample.tqi.com.br.planodecarreira.f_perfil_acesso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_Tutor.HomeTutorActivity;
import sample.tqi.com.br.planodecarreira.f_home_talento.HomeTalentoActivity;
import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class PerfilAcessoActivity extends Activity implements PerfilAcessoView {

    private PerfilAcessoPresenter presenter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_perfil_acesso );

        presenter = new PerfilAcessoPresenter();
        presenter.attachView(this);
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
    public void buildAdapter( ArrayList<PerfilAcesso> lista ) {
        if (lista.size() == 1) {
            if (lista.get( 0 ).getDescription().equals( "Talento" )) {
                Intent intent  = new Intent(this, HomeTalentoActivity.class);
                intent.putExtra( "lista", lista );
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            } else {
                Intent intent  = new Intent(this, HomeTutorActivity.class);
                intent.setFlags (FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(intent);
                finish();

            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById( R.id.rv_perfil );
        recyclerView.setAdapter( new RecyclerAdapter( this, (lista) ) );
        RecyclerView.LayoutManager layout = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
        recyclerView.setLayoutManager( layout );
    }
}