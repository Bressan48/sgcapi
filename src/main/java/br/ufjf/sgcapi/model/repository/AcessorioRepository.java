package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.Acessorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {
}
