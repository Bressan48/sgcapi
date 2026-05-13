package br.ufjf.sgcapi.model.repository;

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
    private FormaDePagamentoRepository formaDePagamento;

    @ManyToOne
    private FuncionarioRepository funcionario;

    @ManyToOne
    private ClienteRepository cliente;

}
