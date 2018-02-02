package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by katia.goncalves on 31/01/2018.
 */

public class Tutor implements Serializable {
    private Long id_talento;
    private Long id_modulo;
    private String nome;
    private String unidade;
    private String status;
    private String modulo;
    private String nivel;
    private String area;
    private String data_fim;

    public Long getId_talento() {
        return id_talento;
    }

    public void setId_talento( Long id_talento ) {
        this.id_talento = id_talento;
    }

    public Long getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo( Long id_modulo ) {
        this.id_modulo = id_modulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade( String unidade ) {
        this.unidade = unidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo( String modulo ) {
        this.modulo = modulo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel( String nivel ) {
        this.nivel = nivel;
    }

    public String getArea() {
        return area;
    }

    public void setArea( String area ) {
        this.area = area;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim( String data_fim ) {
        this.data_fim = data_fim;
    }
}
