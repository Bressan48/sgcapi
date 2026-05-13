package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository <Pessoa, Long>{
}
