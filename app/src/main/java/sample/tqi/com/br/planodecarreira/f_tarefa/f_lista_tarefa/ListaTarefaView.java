package sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa;

import android.content.Context;

import java.util.List;

import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;

/**
 * Created by alexandre.azevedo on 19/01/2018.
 */

public interface ListaTarefaView {

    void showSuccess(Tarefa tarefa);

    void buildList(List<Tarefa> tarefas);

    void showError(String message);

    void dettachView(Context context );


}
