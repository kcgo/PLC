package sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

public class ListaTarefaActivity extends AppCompatActivity implements ListaTarefaView {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ListaTarefaPresenter presenter;
    private WaitDialog waitDialog;
    private CardView cardView;
    private int idModulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefa);
        initComponents();
    }

    private void initComponents() {
        findViewById();

        waitDialog = new WaitDialog(this);
        presenter = new ListaTarefaPresenter();
        presenter.attachView(this);
        toolbar.setTitle(getString(R.string.st_list_task));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getBundle();
    }

    private void findViewById() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_list_task);
        toolbar = (Toolbar) findViewById(R.id.tb_activity_lista_tarefa);
        cardView = (CardView) findViewById(R.id.card_empty);
    }

    public void getBundle() {
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        idModulo = params.getInt("idModulo", 0);
        loadData(idModulo);
    }

    public void loadData(int idModulo) {
        waitDialog.show();
        presenter.getListaTarefa(this, idModulo);
    }

    @Override
    public void showSuccess(Tarefa tarefa) {

    }

    @Override
    public void buildList(List<Tarefa> tarefas) {
        waitDialog.dismiss();
        if ((tarefas != null) && (tarefas.size() != 0)) {
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setHasFixedSize(true);

            ListaTarefaAdapter adapter = new ListaTarefaAdapter(this, tarefas);
            recyclerView.setAdapter(adapter);
        } else {
            cardView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        waitDialog.dismiss();
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void dettachView( Context context ) {

    }


}
