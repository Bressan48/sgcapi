package br.ufjf.sgcapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Funcionario extends Pessoa{
    private String login;
    private String senha;
    private boolean admin;
}
