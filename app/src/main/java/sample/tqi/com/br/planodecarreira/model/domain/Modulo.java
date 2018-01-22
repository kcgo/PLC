package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by alexandre.azevedo on 18/01/2018.
 */

public class Modulo implements Serializable {

    private Long id;
    private String nome;
    private String nivel;
    private String area;
    private String data_final;
    private Integer tarefas_total;
    private Integer tarefas_concluidas;

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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getData_final() {
        return data_final;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }

    public Integer getTarefas_total() {
        return tarefas_total;
    }

    public void setTarefas_total(Integer tarefas_total) {
        this.tarefas_total = tarefas_total;
    }

    public Integer getTarefas_concluidas() {
        return tarefas_concluidas;
    }

    public void setTarefas_concluidas(Integer tarefas_concluidas) {
        this.tarefas_concluidas = tarefas_concluidas;
    }
}
