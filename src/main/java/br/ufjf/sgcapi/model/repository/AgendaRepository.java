package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository <Agenda, Long>{
}

