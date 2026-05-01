package br.ufjf.sgcapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NomeDeCarro nome;
    @ManyToOne
    private Combustivel combustivel;
    @ManyToOne
    private Direcao direcao;
    @ManyToOne
    private Modelo modelo;

    private Integer anoModelo;
    private Integer anoFabricacao;
    private String chassi;
    private String cor;
    private String placa;
    private Float precoInicial;
    private Boolean foiVendido;

    @ManyToOne
    private Venda venda;

}
