package sample.tqi.com.br.planodecarreira.f_modulo;

import android.content.Context;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;

import sample.tqi.com.br.planodecarreira.model.domain.Feedback;
import sample.tqi.com.br.planodecarreira.model.domain.Modulo;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

import static sample.tqi.com.br.planodecarreira.util.UIUtil.getDataConvert;

public class ModuloActivity extends AppCompatActivity implements ModuloView {

    private Toolbar toolbar;
//    private ScrollView scrollView;
    private RecyclerView recyclerView;
    private WaitDialog waitDialog;
    private ModuloActivityPresenter presenter;
    private TextView txtDataInicial;
    private TextView txtDataFinal;
    private TextView txtEstadoStatus;
    private TextView txtFeedback;
    private TextView txtNomeModulo;
    private TextView txtNomeTutor;
    private int idModulo;
    private Modulo modulo;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_modulo );
        initComponents();
    }

    private void initComponents() {
        getBundle();
        findViewById();

        toolbar.setTitle(getString(R.string.st_module));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        waitDialog = new WaitDialog( this );
        presenter = new ModuloActivityPresenter();
        presenter.attachView( this );
        toolbar.setTitle( "Modulo" );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                finish();
            }
        } );
//        scrollView.smoothScrollTo( 0, 0 );
        loadData();
    }

    public void getBundle(){
        modulo = (Modulo) getIntent().getExtras().getSerializable("modulo");
    }

    private void findViewById() {
        toolbar = (Toolbar) findViewById( R.id.tb_activity_modulo );
        recyclerView = (RecyclerView) findViewById( R.id.rv_modulo );
        txtDataInicial = (TextView) findViewById( R.id.tv_data_inicial );
        txtDataFinal = (TextView) findViewById( R.id.tv_data_final );
        txtEstadoStatus = (TextView) findViewById( R.id.tv_estado_status );
        txtFeedback = (TextView) findViewById( R.id.tv_feedback );
        txtNomeModulo = (TextView) findViewById( R.id.tv_descricao_modulo );
        txtNomeTutor = (TextView) findViewById( R.id.tv_nome_tutor_modulo );
    }

    public void loadData() {
        waitDialog.show();
        buildScreen();
        presenter.getFeedback( this ,modulo.getId_talento().intValue(), modulo.getId_modulo().intValue());
    }

    public void buildScreen(){
        txtNomeModulo.setText(modulo.getNome_modulo());
        txtNomeTutor.setText(modulo.getTutor());
        txtDataInicial.setText((modulo.getData_inicio() == null? "" : getDataConvert(modulo.getData_inicio())));
        txtDataFinal.setText((modulo.getData_fim() == null? "" : getDataConvert(modulo.getData_fim())));
        txtEstadoStatus.setText(modulo.getStatus());
    }

    @Override
    public void showSuccess( Modulo modulo ) {
        waitDialog.dismiss();
    }

    @Override
    public void showError( String string ) {
        waitDialog.dismiss();
        Context context = getApplicationContext();
        CharSequence text = "message";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText( context, text, duration );
        toast.show();
    }

    @Override
    public void buildList( List <Modulo> modulos ) {

    }

    @Override
    public void buildFeedBackList(List<Feedback> feedbacks) {
        waitDialog.dismiss();
        if ((feedbacks != null) && (feedbacks.size() != 0)) {
            recyclerView.setHasFixedSize( true );

            LinearLayoutManager llm = new LinearLayoutManager( this );
            llm.setOrientation( LinearLayoutManager.VERTICAL );
            recyclerView.setLayoutManager( llm );
            ModuloAdapter adapter = new ModuloAdapter( this , feedbacks);
            recyclerView.setAdapter( adapter );
        } else {
            recyclerView.setVisibility( View.GONE );
        }
    }
}