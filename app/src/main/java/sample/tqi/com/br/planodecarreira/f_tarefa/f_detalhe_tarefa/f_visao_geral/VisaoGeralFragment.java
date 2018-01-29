package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_visao_geral;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.roughike.bottombar.BottomBar;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.DetalheTarefaPresenter;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.DetalheTarefaView;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa.ListaTarefaView;
import sample.tqi.com.br.planodecarreira.model.domain.Comentario;
import sample.tqi.com.br.planodecarreira.model.domain.Log;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisaoGeralFragment extends Fragment {

    private EditText edtNome;
    private EditText edtDescricao;
    private Tarefa tarefa;

//    private DetalheTarefaPresenter presenter;
//    private WaitDialog waitDialog;

    public VisaoGeralFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_visao_geral, container, false);
        initComponents(layout);
        return layout;
    }

    private void initComponents(View view) {
        findViewById(view);

//        presenter = new DetalheTarefaPresenter();
//        presenter.attachView(this);

//        waitDialog = new WaitDialog(getActivity());
        getBundle();
    }

    public void getBundle(){
        tarefa = (Tarefa) getArguments().getSerializable("tarefa");
        buildScreen(tarefa);
    }

//    public void loadData() {
//        new Handler().post(() -> {
//            waitDialog.show();
//            presenter.getVisaoGeralTarefa(getActivity(), idTarefa, idTalento, idModulo);
//        });
//    }

    private void findViewById(View view) {
        edtNome = (EditText) view.findViewById(R.id.edt_nome_tarefa_visao_geral);
        edtDescricao = (EditText) view.findViewById(R.id.edt_descricao_tarefa_visao_geral);
    }

    public void buildScreen(Tarefa tarefa) {
//        waitDialog.dismiss();
        edtNome.setText(tarefa.getNome_tarefa());
        edtDescricao.setText(tarefa.getDescricao_tarefa());
    }
}