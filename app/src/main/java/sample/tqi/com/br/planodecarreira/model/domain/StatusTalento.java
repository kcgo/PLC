package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by katia.goncalves on 30/01/2018.
 */

public class StatusTalento implements Serializable {
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao( String observacao ) {
        this.observacao = observacao;
    }
}
