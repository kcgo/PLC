package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by alexandre.azevedo on 25/01/2018.
 */

public class Feedback implements Serializable {

    private String remtente;
    private String feedback;

    public String getRemtente() {
        return remtente;
    }

    public void setRemtente(String remtente) {
        this.remtente = remtente;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
