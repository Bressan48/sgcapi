package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaRepository extends JpaRepository <Agencia, Long>{
}

