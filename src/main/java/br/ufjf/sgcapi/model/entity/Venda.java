package br.ufjf.sgcapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float valorVenda;

    @ManyToOne
    private FormaDePagamento formaDePagamento;

    @ManyToOne
    private Vendedor vendedor;

    @ManyToOne
    private Cliente cliente;

}
