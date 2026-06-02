package br.ufjf.sgcapi.model.repository;
import br.ufjf.sgcapi.model.entity.CarroUsado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroUsadoRepository extends JpaRepository<CarroUsado, Long> {
}