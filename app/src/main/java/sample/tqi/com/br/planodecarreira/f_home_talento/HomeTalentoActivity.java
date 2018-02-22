package sample.tqi.com.br.planodecarreira.f_home_talento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_login.LoginActivity;
import sample.tqi.com.br.planodecarreira.f_login.LoginPresenter;
import sample.tqi.com.br.planodecarreira.f_perfil_acesso.PerfilAcessoActivity;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa.ListaTarefaActivity;
import sample.tqi.com.br.planodecarreira.model.domain.Modulo;
import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

public class HomeTalentoActivity extends AppCompatActivity implements HomeTalentoView {

    private RecyclerView recyclerView;
    private ScrollView scrollView;
    private LoginPresenter loginPresenter;
    private Button btnTask;
    private Toolbar toolbar;
    private TextView txtNomeTutor;
    private TextView txtModulo;
    private TextView txtDataFinal;
    private TextView txtCount;
    private CardView cardView;
    private WaitDialog waitDialog;
    private HomeTalentoPresenter presenter;
    private Bundle bundle;
    private Modulo modulo;
    private Long idTalento;
    ArrayList <PerfilAcesso> lista;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_talento );
        initComponents();
    }
    private void initComponents() {
        findViewById();
        loginPresenter = new LoginPresenter();
        modulo = new Modulo();
        waitDialog = new WaitDialog( this );
        presenter = new HomeTalentoPresenter();
        presenter.attachView( this );
        presenter.saveTypeAccess( "talento" );
        toolbar.setTitle( getString( R.string.st_home_talent ) );
        setSupportActionBar( toolbar );
        //getSupportActionBar().setDisplayHomeAsUpEnabled( true );


        scrollView.smoothScrollTo( 0, 0 );
        btnTask.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                bundle = new Bundle();
                bundle.putInt( "idModulo", modulo.getId_modulo().intValue() );
                Intent intent = new Intent( getApplicationContext(), ListaTarefaActivity.class );
                intent.putExtras( bundle );
                startActivity( intent );
            }

        } );
        loadData();
    }

    private void findViewById() {
        recyclerView = (RecyclerView) findViewById( R.id.rv_career_history );
        scrollView = (ScrollView) findViewById( R.id.sv_home_talento );
        btnTask = (Button) findViewById( R.id.btn_task_home_talento );
        toolbar = (Toolbar) findViewById( R.id.tb_activity_home_talento );
        txtModulo = (TextView) findViewById( R.id.tv_modulo_title );
        txtDataFinal = (TextView) findViewById( R.id.tv_data_value );
        txtCount = (TextView) findViewById( R.id.tv_count );
        cardView = (CardView) findViewById( R.id.card_empty );
        txtNomeTutor = (TextView) findViewById( R.id.tv_nome_tutor );

    }
    public void loadData() {
        waitDialog.show();
        presenter.getModuloVigente( this );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu, menu );
        ArrayList<PerfilAcesso> lista = (ArrayList <PerfilAcesso>) getIntent().getSerializableExtra( "lista" );

        if (lista!=null && lista.size()==1 && lista.get( 0 ).getDescription().equals( "Talento" ))
            menu.getItem( 1 ).setVisible( false );

        return super.onCreateOptionsMenu( menu );
    }

    @Override

    public boolean onOptionItemSelected( MenuItem item ) {
        return super.onOptionsItemSelected( item );
    }
    @Override
    public void AlterarPerfil( MenuItem item ) {
        Intent intent = new Intent( this, PerfilAcessoActivity.class );
        startActivity( intent );
        finish();

    }

     public void sair( MenuItem item ) {
        Intent intent = new Intent( this, LoginActivity.class );
        finish();
        startActivity( intent );
    }

    @Override
    public void showSuccess( Modulo modulo ) {
//        waitDialog.dismiss();
        this.modulo = modulo;
        this.idTalento = modulo.getId_talento();
        txtNomeTutor.setText( modulo.getNome_tutor() );
        txtModulo.setText( modulo.getNome_modulo() );
        //txtDataFinal.setText( (modulo.getData_fim() == null ? "" : getDataConvert( modulo.getData_fim() )) );
        txtDataFinal.setText( modulo.getData_fim() );
        txtCount.setText( modulo.getTarefas_concluidas().toString() + " / " + modulo.getTarefas_totais().toString() );
        //presenter.getModuloVigente( this );
        presenter.getHistorico( this );
    }

    @Override
    public void buildList( List <Modulo> modulos ) {
        waitDialog.dismiss();
        if ((modulos != null) && (modulos.size() != 0)) {
            recyclerView.setHasFixedSize( true );

            LinearLayoutManager llm = new LinearLayoutManager( this );
            llm.setOrientation( LinearLayoutManager.VERTICAL );
            recyclerView.setLayoutManager( llm );
            HomeTalentoAdapter adapter = new HomeTalentoAdapter( this, modulos, idTalento );
            recyclerView.setAdapter( adapter );
        } else {
            cardView.setVisibility( View.VISIBLE );
            recyclerView.setVisibility( View.GONE );
        }
    }

    @Override
    public void showError( String message ) {
        waitDialog.dismiss();
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText( context, text, duration );
        toast.show();
    }


}