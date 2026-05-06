package br.ufjf.sgcapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Estado estado;
    @ManyToOne
    private Cidade cidade;

    private String endereco;

    private Float valorEmCaixa;

}

