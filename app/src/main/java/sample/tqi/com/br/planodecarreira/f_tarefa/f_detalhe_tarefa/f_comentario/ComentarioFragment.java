package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.Comentario;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComentarioFragment extends Fragment implements ComentarioView{

    private Button btn_enviar_comentario;
    private EditText edtObservacao;
    private RecyclerView recyclerView;
    private CardView cardView;
    private ComentarioPresenter presenter;
    private WaitDialog waitDialog;
    private Tarefa tarefa;

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

        presenter = new ComentarioPresenter();
        presenter.attachView(this);

        waitDialog = new WaitDialog(getActivity());

        setUpAction();
        getBundle();
     //    loadData( );

    }
    public void getBundle(){
        tarefa = (Tarefa) getArguments().getSerializable("tarefa");
        loadData(tarefa);
    }

    public void loadData( Tarefa tarefa) {
        new Handler().post(() -> {
            waitDialog.show();
            presenter.getComentarioTarefa( getContext(), tarefa.getId_talento().intValue(), tarefa.getId_modulo().intValue(), tarefa.getId_tarefa().intValue());
        });
    }

    private void findViewById(View view) {
        recyclerView =  view.findViewById(R.id.rv_list_comment);
        cardView =  view.findViewById(R.id.card_empty);
        btn_enviar_comentario= view.findViewById(R.id.btn_enviar_comentario );
        edtObservacao = view.findViewById(R.id.edt_txt_observacao_comentario );
    }
    private void setUpAction(){
        btn_enviar_comentario.setOnClickListener( ( View v ) -> {
            Comentario comentario = new Comentario();
            comentario.setComentario(edtObservacao.getText().toString());
            presenter.postComentario( getActivity(), comentario, tarefa );
        } );
    }

    @Override
    public void buildComentarioList( List <Comentario> comentarios) {
        waitDialog.dismiss();
        if ((comentarios != null) && (comentarios.size() != 0)) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            ComentarioAdapter adapter = new ComentarioAdapter(getContext(), comentarios);
            recyclerView.setAdapter(adapter);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                recyclerView.setNestedScrollingEnabled(false);
            }
//            adapter.notifyDataSetChanged();
        } else {
            cardView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError( String message ) {
        waitDialog.dismiss();
        Context context = getActivity();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText( context, text, duration );
        toast.show();

    }

    @Override
    public void showSuccess( retrofit2.Response<String> result ) {
        edtObservacao.setText("");
        presenter.getComentarioTarefa( getContext(), tarefa.getId_talento().intValue(), tarefa.getId_modulo().intValue(), tarefa.getId_tarefa().intValue());
    }

}