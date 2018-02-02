package sample.tqi.com.br.planodecarreira.model.domain;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by alexandre.azevedo on 23/01/2018.
 */

public class Comentario implements Serializable {

    @Expose
    private String remetente;
    @Expose
    private String comentario;

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente( String remtente ) {
        this.remetente = remtente;
    }

    public String getComentario() {
        return comentario;
    }


    public void setComentario( String comentario ) {
        this.comentario = comentario;
    }
}
