package br.ufjf.sgcapi.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class CarroDeTestDrive extends Carro{

    @ManyToOne
    private TestDrive testDrive;
}
