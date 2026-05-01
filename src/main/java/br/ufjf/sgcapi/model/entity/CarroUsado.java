package br.ufjf.sgcapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroUsado extends Carro{

    private Float kmRodados;

    private String donoAnterior;

    private Boolean docEmDia;

    private Float precoTabelaFipe;

}
