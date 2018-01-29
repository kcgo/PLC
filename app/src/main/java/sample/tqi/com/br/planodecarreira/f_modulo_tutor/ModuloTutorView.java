package sample.tqi.com.br.planodecarreira.f_modulo_tutor;

import java.util.List;

import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;

/**
 * Created by alexandre.azevedo on 25/01/2018.
 */

public interface ModuloTutorView {

    void showError( String string );

    void buildTarefaList( List<Tarefa> tarefas );
}

