package br.com.up_down_list.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "ArquivoEntity")
public class ArquivoEntity implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;
    @Column (name = "nome")
    private String nome;
    @Column (name = "contentType")
    private String contentType;
    @Column (name = "conteudo")
    private byte[] conteudo;
}
