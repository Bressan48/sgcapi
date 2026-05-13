package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository <Gerente, Long>{
}
