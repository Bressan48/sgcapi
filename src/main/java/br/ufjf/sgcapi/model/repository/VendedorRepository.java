package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository <Vendedor, Long>{
}
