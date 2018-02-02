package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by katia.goncalves on 30/01/2018.
 */

public class StatusTutor implements Serializable {
    private String status;
    private String observacao;

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao( String observacao ) {
        this.observacao = observacao;
    }
}


