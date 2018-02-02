package sample.tqi.com.br.planodecarreira.f_modulo_tutor;

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
import sample.tqi.com.br.planodecarreira.model.domain.Talento;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

import static sample.tqi.com.br.planodecarreira.util.UIUtil.getDataConvert;

public class ModuloTutorActivity extends AppCompatActivity implements ModuloTutorView {

    private Toolbar toolbar;
    //    private ScrollView scrollView;
    private RecyclerView recyclerView;
    private WaitDialog waitDialog;
    private ModuloTutorPresenter presenter;
    private Talento talento;
    private TextView txtNomeModulo;
    private TextView txtDataFinal;
    private TextView txtNivel;
    private TextView txtArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_tutor);
        initComponents();
    }

    private void initComponents() {
        findViewById();

        waitDialog = new WaitDialog( this );
        presenter = new ModuloTutorPresenter();
        presenter.attachView(this);
        toolbar.setTitle( "Modulo vigente do talento" );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                finish();
            }
        } );
//        scrollView.smoothScrollTo( 0, 0 );
        getBundle();
        loadData();
    }

    public void getBundle(){
        talento = (Talento) getIntent().getExtras().getSerializable("talento");
        buildScreen();
    }

    private void findViewById() {
        toolbar = (Toolbar) findViewById( R.id.tb_activity_modulo_tutor );
        recyclerView = (RecyclerView) findViewById( R.id.rv_modulo_tutor );
        txtDataFinal = (TextView) findViewById( R.id.txt_data_final_modulo_tutor );
        txtArea = (TextView) findViewById( R.id.txt_area_modulo_tutor );
        txtNivel = (TextView) findViewById( R.id.txt_nivel_modulo_tutor );
        txtNomeModulo = (TextView) findViewById( R.id.tv_descricao_modulo_tutor );
    }

    public void loadData() {
        waitDialog.show();

        presenter.getTarefa( this , talento.getId_talento().intValue(), talento.getId_modulo().intValue());
    }

    public void buildScreen(){
        txtNomeModulo.setText(talento.getModulo());
        txtDataFinal.setText((talento.getData_fim() == null? "" : getDataConvert(talento.getData_fim())));
        txtArea.setText(talento.getArea());
        txtNivel.setText(talento.getNivel());
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
    public void buildTarefaList(List<Tarefa> tarefas) {
        waitDialog.dismiss();
        if ((tarefas != null) && (tarefas.size() != 0)) {
            recyclerView.setHasFixedSize( true );

            LinearLayoutManager llm = new LinearLayoutManager( this );
            llm.setOrientation( LinearLayoutManager.VERTICAL );
            recyclerView.setLayoutManager( llm );
            ModuloTutorAdapter adapter = new ModuloTutorAdapter( this , tarefas);
            recyclerView.setAdapter( adapter );
        } else {
            recyclerView.setVisibility( View.GONE );
        }
    }
}
