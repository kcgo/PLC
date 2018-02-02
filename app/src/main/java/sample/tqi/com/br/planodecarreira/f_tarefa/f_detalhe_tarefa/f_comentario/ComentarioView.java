package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario;

import java.util.List;

import sample.tqi.com.br.planodecarreira.model.domain.Comentario;

/**
 * Created by katia.goncalves on 30/01/2018.
 */

public interface ComentarioView {

    void showSuccess( retrofit2.Response<String> result );

    void buildComentarioList( List <Comentario> comentarios);

    void showError(String message);
}
