package sample.tqi.com.br.planodecarreira.f_Tutor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.Talento;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

import static sample.tqi.com.br.planodecarreira.R.id.tb_activity_talentos;

public class HomeTutorActivity extends AppCompatActivity implements Serializable, HomeTutorView {

    private HomeTutorPresenter presenter;
    private Spinner spn1;
    private List <String> estado = new ArrayList <String>();
    private String status;
    private Toolbar toolbar;
    private WaitDialog waitDialog;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_tutor );
        initComponents();

        presenter = new HomeTutorPresenter();
        presenter.attachView(this);
        presenter.saveTypeAccess("tutor");
        presenter.getHomeTutor( HomeTutorActivity.this, "todos" );
        estado.add( "Avaliação" );
        estado.add( "Concluídos" );
        estado.add( "Todos" );

        ArrayList<String> optionsAction = new ArrayList<String>();
        optionsAction.add( "avaliacao" );
        optionsAction.add( "concluidos" );
        optionsAction.add( "todos" );

        Spinner spn1 = new Spinner( this );
        ArrayAdapter <String> arrayAdapter = new ArrayAdapter <String>( this, android.R.layout.simple_list_item_1, estado );
        ArrayAdapter <String> spinnerArrayAdapter = arrayAdapter;
        spn1 = findViewById( R.id.spinner );
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_item );
        spn1.setAdapter( spinnerArrayAdapter );
        spn1.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected( AdapterView <?> parent, View v, int posicao, long id ) {
                //pega nome pela posição

//                status = parent.getItemAtPosition( posicao ).toString();
                status = optionsAction.get(posicao).toString();
                presenter.getHomeTutor( getApplicationContext(), status );
            }

            @Override
            public void onNothingSelected( AdapterView <?> parent ) {

            }

        } );
    }
    private void initComponents() {

        toolbar = findViewById( tb_activity_talentos );
        toolbar.setTitle( "Home" );
        toolbar.getContentInsetStartWithNavigation();
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                finish();
            }
        } );
    }


    @Override
    public void showError( String message ) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText( context, text, duration );
        toast.show();
    }

    @Override
    public void buildAdapter( List <Talento> lista ) {
        RecyclerView recyclerView = (RecyclerView) findViewById( R.id.rv_home_tutor );
        recyclerView.setAdapter( new RecyclerAdapterTutor( this, (lista) ) );
        RecyclerView.LayoutManager layout = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
        recyclerView.setLayoutManager( layout );
    }
}




