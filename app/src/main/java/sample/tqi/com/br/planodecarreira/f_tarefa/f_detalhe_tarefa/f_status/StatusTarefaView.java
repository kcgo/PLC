package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_status;

import retrofit2.Response;
import sample.tqi.com.br.planodecarreira.model.domain.AlterarStatusResponse;

/**
 * Created by alexandre.azevedo on 22/01/2018.
 */

public interface StatusTarefaView {

    void showSucess( AlterarStatusResponse alterarStatusTutor );

    void showSucessTalento(AlterarStatusResponse alterarStatusTalento);

    void showError( String string );

}
