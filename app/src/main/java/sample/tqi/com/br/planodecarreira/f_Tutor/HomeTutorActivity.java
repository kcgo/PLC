package sample.tqi.com.br.planodecarreira.f_Tutor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.ListaTalentos;

public class HomeTutorActivity extends Activity implements HomeTutorView {

    private HomeTutorPresenter presenter;
    private Spinner spn1;
    private List <String> estado = new ArrayList <String>();
    private String status;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_tutor );
        presenter = new HomeTutorPresenter();
        presenter.attachView( this );
        presenter.getHomeTutor( HomeTutorActivity.this, estado );
        estado.add( " Em Avaliação " );
        estado.add( " Concluido " );
        estado.add( " Todos " );

        Spinner spn1 = new Spinner(this);

        ArrayAdapter <String> arrayAdapter = new ArrayAdapter <String>( this, android.R.layout.simple_spinner_dropdown_item, estado );
        ArrayAdapter <String> spinnerArrayAdapter = arrayAdapter;
        spn1 = findViewById( R.id.spinner );
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_item );
        spn1.setAdapter( spinnerArrayAdapter );
        spn1.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected( AdapterView <?> parent, View v, int posicao, long id ) {
                //pega nome pela posição

                status = parent.getItemAtPosition( posicao ).toString();
                presenter.getHomeTutor(getApplicationContext(), estado );
        }

            @Override
            public void onNothingSelected( AdapterView <?> parent ) {

            }
        } );

    }

    @Override
    public void showError( String string ) {
        Context context = getApplicationContext();
        CharSequence text = " ";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText( context, text, duration );
        toast.show();
    }

    @Override
    public void buildAdapter( List <ListaTalentos> lista ) {
        RecyclerView recyclerView = (RecyclerView) findViewById( R.id.rv_home_tutor );
        recyclerView.setAdapter( new RecyclerAdapterTutor( this, (lista) ) );
        RecyclerView.LayoutManager layout = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
        recyclerView.setLayoutManager( layout );
    }
}




