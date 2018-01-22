package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by alexandre.azevedo on 19/01/2018.
 */

public class Tarefa implements Serializable {

    private Long id;
    private String nome;
    private Long peso;
    private String status;
    private String data_atribuicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_atribuicao() {
        return data_atribuicao;
    }

    public void setData_atribuicao(String data_atribuicao) {
        this.data_atribuicao = data_atribuicao;
    }
}
