package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario;

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
public class ComentarioFragment extends Fragment implements DetalheTarefaView{

    private RecyclerView recyclerView;
    private CardView cardView;
    private DetalheTarefaPresenter presenter;
    private WaitDialog waitDialog;

    public ComentarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_comentario, container, false);
        initComponents(layout);
        return layout;
    }
    private void initComponents(View view) {
        findViewById(view);

        presenter = new DetalheTarefaPresenter();
        presenter.attachView(this);

        waitDialog = new WaitDialog(getActivity());
//        loadData();
    }

    public void loadData() {
        new Handler().post(() -> {
            waitDialog.show();
            presenter.getComentarioTarefa(getActivity());
        });
    }

    private void findViewById(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_comment);
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

    }

    @Override
    public void buildComentarioList( List <Comentario> comentarios) {
        waitDialog.dismiss();
        if ((comentarios != null) && (comentarios.size() != 0)) {
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            ComentarioAdapter adapter = new ComentarioAdapter(getContext(), comentarios);
            recyclerView.setAdapter(adapter);
        } else {
            cardView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {

    }
}