package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa;

import java.util.List;

import sample.tqi.com.br.planodecarreira.model.domain.Comentario;
import sample.tqi.com.br.planodecarreira.model.domain.Log;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;

/**
 * Created by alexandre.azevedo on 22/01/2018.
 */

public interface DetalheTarefaView {

    void showSuccess(Tarefa tarefa);

    void buildList(List<Tarefa> tarefas);

    void buildLogList(List<Log> logs);

    void buildComentarioList( List <Comentario> comentarios);

    void showError(String message);


}
