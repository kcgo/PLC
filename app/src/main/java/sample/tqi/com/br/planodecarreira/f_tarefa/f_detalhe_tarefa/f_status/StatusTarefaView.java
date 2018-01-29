package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_status;

/**
 * Created by alexandre.azevedo on 22/01/2018.
 */

public interface StatusTarefaView {

    void showSuccess( retrofit2.Response<String> result );

    void showError( String message );

}
