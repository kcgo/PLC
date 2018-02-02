package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by alexandre.azevedo on 01/02/2018.
 */

public class AlterarStatusResponse implements Serializable {

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
