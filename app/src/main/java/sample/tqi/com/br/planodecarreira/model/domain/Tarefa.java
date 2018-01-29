package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by alexandre.azevedo on 19/01/2018.
 */

public class Tarefa implements Serializable {

    private Long id_tarefa;
    private Long id_talento;
    private Long id_modulo;
    private String nome_tarefa;
    private String descricao_tarefa;
    private String estado_tarefa;
    private String peso_tarefa;
    private String data_alteracao_tarefa;

    public Long getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(Long id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public Long getId_talento() {
        return id_talento;
    }

    public void setId_talento(Long id_talento) {
        this.id_talento = id_talento;
    }

    public Long getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(Long id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getNome_tarefa() {
        return nome_tarefa;
    }

    public void setNome_tarefa(String nome_tarefa) {
        this.nome_tarefa = nome_tarefa;
    }

    public String getDescricao_tarefa() {
        return descricao_tarefa;
    }

    public void setDescricao_tarefa(String descricao_tarefa) {
        this.descricao_tarefa = descricao_tarefa;
    }

    public String getEstado_tarefa() {
        return estado_tarefa;
    }

    public void setEstado_tarefa(String estado_tarefa) {
        this.estado_tarefa = estado_tarefa;
    }

    public String getPeso_tarefa() {
        return peso_tarefa;
    }

    public void setPeso_tarefa(String peso_tarefa) {
        this.peso_tarefa = peso_tarefa;
    }

    public String getData_alteracao_tarefa() {
        return data_alteracao_tarefa;
    }

    public void setData_alteracao_tarefa(String data_alteracao_tarefa) {
        this.data_alteracao_tarefa = data_alteracao_tarefa;
    }
}
