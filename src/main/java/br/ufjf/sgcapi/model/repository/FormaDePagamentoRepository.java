package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaDePagamentoRepository extends JpaRepository <FormaDePagamento, Long>{
}
