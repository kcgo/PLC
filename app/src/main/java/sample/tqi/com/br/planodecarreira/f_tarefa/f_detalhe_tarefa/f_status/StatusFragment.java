package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_status;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Response;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.Comentario;
import sample.tqi.com.br.planodecarreira.model.domain.Talento;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment implements StatusTarefaView {

    private EditText edtObservacao;
    private Button btnEnviar;
    private Talento talento;
    private StatusTarefaPresenter presenter;
    private WaitDialog waitDialog;

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_status, container, false);
        initComponents(layout);
        return layout;
    }

      private void initComponents(View view) {
        findViewById(view);
        setUpAction();

        presenter = new StatusTarefaPresenter();
        presenter.attachView(this);

        waitDialog = new WaitDialog(getActivity());
        loadData( talento );
    }

    public void loadData( Talento talento ) {
        new Handler().post(() -> {
//            waitDialog.show();
//            presenter.getVisaoGeralTarefa(getActivity());

        });
    }

    private void findViewById(View view) {
        edtObservacao = view.findViewById(R.id.edt_txt_observacao_status);
        btnEnviar = view.findViewById(R.id.btn_enviar_status);

    }
    private void setUpAction(){
        btnEnviar.setOnClickListener( v -> {
            Comentario comentario = new Comentario();
            comentario.setComentario( edtObservacao.getText().toString());
            presenter.postComentario( getActivity(), comentario);
        } );
 }

    @Override
    public void showSuccess( Response<String> result ) {
        waitDialog.dismiss();

    }

    @Override
    public void showError(String message) {
        waitDialog.dismiss();
    }


}
