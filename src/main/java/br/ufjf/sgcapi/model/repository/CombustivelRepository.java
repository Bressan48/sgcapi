package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombustivelRepository extends JpaRepository <Combustivel, Long>{
}
