package br.com.up_down_list.dto;

import br.com.up_down_list.entity.ArquivoEntity;

import java.io.Serializable;

public class IdNomeDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long id;
    private String nome;

    public IdNomeDTO() {
    }

    public IdNomeDTO(ArquivoEntity obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

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
}
