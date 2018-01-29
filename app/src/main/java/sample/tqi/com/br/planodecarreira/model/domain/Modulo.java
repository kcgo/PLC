package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by alexandre.azevedo on 18/01/2018.
 */

public class Modulo implements Serializable {

    private Long id_modulo;
    private Long id_talento;
    private String nome_modulo;
    private String tutor;
    private String nivel;
    private String area;
    private String data_inicio;
    private String data_fim;
    private String status;
    private Integer tarefas_totais;
    private Integer tarefas_concluidas;


    public Long getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(Long id_modulo) {
        this.id_modulo = id_modulo;
    }

    public Long getId_talento() {
        return id_talento;
    }

    public void setId_talento(Long id_talento) {
        this.id_talento = id_talento;
    }

    public String getNome_modulo() {
        return nome_modulo;
    }

    public void setNome_modulo(String nome_modulo) {
        this.nome_modulo = nome_modulo;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
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

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTarefas_totais() {
        return tarefas_totais;
    }

    public void setTarefas_totais(Integer tarefas_totais) {
        this.tarefas_totais = tarefas_totais;
    }

    public Integer getTarefas_concluidas() {
        return tarefas_concluidas;
    }

    public void setTarefas_concluidas(Integer tarefas_concluidas) {
        this.tarefas_concluidas = tarefas_concluidas;
    }
}
