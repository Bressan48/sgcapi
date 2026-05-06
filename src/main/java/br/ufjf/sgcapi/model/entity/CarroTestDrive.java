package br.ufjf.sgcapi.model.entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroTestDrive extends Carro{

    @ManyToOne
    private TestDrive testDrive;
}
