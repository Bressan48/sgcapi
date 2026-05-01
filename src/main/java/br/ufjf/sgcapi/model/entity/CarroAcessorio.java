package br.ufjf.sgcapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroAcessorio {

    @ManyToOne
    private Carro carro;

    @ManyToOne
    private Acessorio acessorio;

}
