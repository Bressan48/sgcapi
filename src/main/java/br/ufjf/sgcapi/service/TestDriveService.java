package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.TestDriveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TestDriveService {
    private TestDriveRepository repository;

    public TestDriveService(TestDriveRepository repository) {
        this.repository = repository;
    }

    public List<TestDrive> getTestDrives() {
        return repository.findAll();
    }

    public Optional<TestDrive> getTestDriveById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public TestDrive salvar(TestDrive estado) {
        validar(estado);
        return repository.save(estado);
    }

    @Transactional
    public void excluir(TestDrive estado) {
        Objects.requireNonNull(estado.getId());
        repository.delete(estado);
    }

    public void validar(TestDrive estado) {

        if (estado.getCliente().getId() == null || estado.getCliente().getId() ==0) {
            throw new RegraNegocioException("Cliente inválida");
        }
        if (estado.getFuncionario().getId() == null || estado.getFuncionario().getId() ==0) {
            throw new RegraNegocioException("Funcionario inválida");
        }

    }
}
