package br.ufjf.sgcapi.model.repository;

import br.ufjf.sgcapi.model.entity.TestDrive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDriveRepository extends JpaRepository <TestDrive, Long>{
}
