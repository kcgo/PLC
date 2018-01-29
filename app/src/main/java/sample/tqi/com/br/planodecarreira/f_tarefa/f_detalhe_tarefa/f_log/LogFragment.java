package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_log;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.DetalheTarefaPresenter;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.DetalheTarefaView;
import sample.tqi.com.br.planodecarreira.model.domain.Comentario;
import sample.tqi.com.br.planodecarreira.model.domain.Log;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogFragment extends Fragment implements DetalheTarefaView{

    private RecyclerView recyclerView;
    private CardView cardView;
    private DetalheTarefaPresenter presenter;
    private WaitDialog waitDialog;
    private Tarefa tarefa;

    public LogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_log, container, false);
        initComponents(layout);
        return layout;
    }

    private void initComponents(View view) {
        findViewById(view);

        presenter = new DetalheTarefaPresenter();
        presenter.attachView(this);

        waitDialog = new WaitDialog(getActivity());

        getBundle();
    }

    public void getBundle(){
        tarefa = (Tarefa) getArguments().getSerializable("tarefa");
        loadData(tarefa);
    }

    public void loadData(Tarefa tarefa) {
        new Handler().post(() -> {
            waitDialog.show();
            presenter.getLogTarefa(getActivity(), tarefa.getId_tarefa().intValue(), tarefa.getId_talento().intValue(), tarefa.getId_modulo().intValue());
        });
    }

    private void findViewById(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_log);
        cardView = (CardView) view.findViewById(R.id.card_empty);
    }

    @Override
    public void showSuccess(Tarefa tarefa) {

    }

    @Override
    public void buildList(List<Tarefa> tarefas) {

    }

    @Override
    public void buildLogList(List<Log> logs) {
        waitDialog.dismiss();
        if ((logs != null) && (logs.size() != 0)) {
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            LogAdapter adapter = new LogAdapter(getContext(), logs);
            recyclerView.setAdapter(adapter);
        } else {
            cardView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void buildComentarioList( List <Comentario> comentarios) {

    }

    @Override
    public void showError(String message) {
        waitDialog.dismiss();
    }
}
