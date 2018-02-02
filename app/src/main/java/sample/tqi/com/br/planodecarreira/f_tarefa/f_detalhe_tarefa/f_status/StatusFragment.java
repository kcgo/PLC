package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_status;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Response;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.DetalheTarefaActivity;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa.ListaTarefaActivity;
import sample.tqi.com.br.planodecarreira.model.domain.AlterarStatusResponse;
import sample.tqi.com.br.planodecarreira.model.domain.StatusTalento;
import sample.tqi.com.br.planodecarreira.model.domain.StatusTutor;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;
import sample.tqi.com.br.planodecarreira.util.AlertMessage;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

import static sample.tqi.com.br.planodecarreira.util.Constants.Alert.titleOk;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment implements StatusTarefaView {

    private EditText edtObservacao;
    private Button btnEnviar;
    private Button btn_rejeitar;
    private Button btn_aprovar;
    private StatusTarefaPresenter presenter;
    private WaitDialog waitDialog;
    private Tarefa tarefa;
    private Bundle bundle;

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate( R.layout.fragment_status, container, false );
        initComponents( layout );
        if (DataStorage.getTypeAccess().equals( getString( R.string.talento ) )) {
            btnEnviar.setVisibility( View.VISIBLE );
        } else if (DataStorage.getTypeAccess().equals( getString( R.string.tutor ) )) {
            btn_rejeitar.setVisibility( View.VISIBLE );
            btn_aprovar.setVisibility( View.VISIBLE );
        }

        return layout;
    }

    private void initComponents( View view ) {
        findViewById( view );
        getBundle();

        presenter = new StatusTarefaPresenter();
        presenter.attachView( this );

        waitDialog = new WaitDialog( getActivity() );

        btn_aprovar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                waitDialog.show();
                StatusTutor statusTutor = new StatusTutor();
                statusTutor.setObservacao( edtObservacao.getText().toString() );
                statusTutor.setStatus( "CONCLUIDA" );
                presenter.putAlterarStatusTutor( getActivity(), statusTutor, tarefa.getId_talento().intValue(),tarefa.getId_modulo().intValue(),tarefa.getId_tarefa().intValue());
            }

        } );
        btn_rejeitar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                waitDialog.show();
                StatusTutor statusTutor = new StatusTutor();
                statusTutor.setObservacao( edtObservacao.getText().toString() );
                statusTutor.setStatus( "FAZER" );
                presenter.putAlterarStatusTutor( getActivity(), statusTutor, tarefa.getId_talento().intValue(),tarefa.getId_modulo().intValue(),tarefa.getId_tarefa().intValue());
            }
        } );
        btnEnviar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                waitDialog.show();
                StatusTalento statusTalento = new StatusTalento();
                statusTalento.setObservacao( edtObservacao.getText().toString() );
                presenter.putAlterarStatusTalento( getActivity(), statusTalento,tarefa.getId_modulo().intValue(),tarefa.getId_tarefa().intValue() );

            }
        } );

    }

    public void getBundle(){
        tarefa = (Tarefa) getArguments().getSerializable("tarefa");
    }

    private void findViewById( View view ) {
        edtObservacao = view.findViewById( R.id.edt_txt_observacao_status );
        btnEnviar = view.findViewById( R.id.btn_enviar_status );
        btn_rejeitar = view.findViewById( R.id.btn_rejeitar );
        btn_aprovar = view.findViewById( R.id.btn_aprovar );
    }

    @Override
    public void showSucess( AlterarStatusResponse alterarStatusTutor ) {
        waitDialog.dismiss();
        DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
                bundle = new Bundle();
                bundle.putInt("idModulo", tarefa.getId_modulo().intValue());
                Intent intent = new Intent(getActivity(), ListaTarefaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        AlertMessage.getInstance(getActivity()).showMessage(titleOk, "Status alterado com sucesso!",positiveButton);
    }

    @Override
    public void showSucessTalento( AlterarStatusResponse alterarStatusTalento ) {
        waitDialog.dismiss();
        DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
                bundle = new Bundle();
                bundle.putInt("idModulo", tarefa.getId_modulo().intValue());
                Intent intent = new Intent(getActivity(), ListaTarefaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        AlertMessage.getInstance(getActivity()).showMessage(titleOk, "Status alterado com sucesso!",positiveButton);
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
}