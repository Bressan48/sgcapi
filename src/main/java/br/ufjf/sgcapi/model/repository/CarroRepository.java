package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository <Carro, Long>{
}
