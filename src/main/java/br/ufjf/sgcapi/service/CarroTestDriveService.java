package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.CarroTestDriveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarroTestDriveService {
    private CarroTestDriveRepository repository;

    public CarroTestDriveService(CarroTestDriveRepository repository) {
        this.repository = repository;
    }

    public List<CarroTestDrive> getCarroTestDrives() {
        return repository.findAll();
    }

    public Optional<CarroTestDrive> getCarroTestDriveById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public CarroTestDrive salvar(CarroTestDrive carroTesteDrive) {
        validar(carroTesteDrive);
        return repository.save(carroTesteDrive);
    }

    @Transactional
    public void excluir(CarroTestDrive carroTesteDrive) {
        Objects.requireNonNull(carroTesteDrive.getId());
        repository.delete(carroTesteDrive);
    }

    public void validar(CarroTestDrive carroTesteDrive) {
        if (carroTesteDrive.getModelo() == null || carroTesteDrive.getModelo().getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do modelo inválida");
        }
        if (carroTesteDrive.getId() == null || carroTesteDrive.getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }
        if (carroTesteDrive.getPlaca() == null || carroTesteDrive.getPlaca().isEmpty()) {
            throw new RegraNegocioException("Placa inválida");
        }
        if (carroTesteDrive.getChassi() == null || carroTesteDrive.getChassi().isEmpty()) {
            throw new RegraNegocioException("Chassi inválido");
        }
        if (carroTesteDrive.getCor() == null || carroTesteDrive.getCor().isEmpty()) {
            throw new RegraNegocioException("Cor inválida");
        }

        if (carroTesteDrive.getAnoFabricacao() == null || carroTesteDrive.getAnoFabricacao() == 0) {
            throw new RegraNegocioException("Ano de Fabricação inválido");
        }
        if (carroTesteDrive.getAnoModelo() == null || carroTesteDrive.getAnoModelo() == 0) {
            throw new RegraNegocioException("Ano do Modelo inválido");
        }
        if (carroTesteDrive.getCombustivel() == null || carroTesteDrive.getCombustivel().getNome().isEmpty()) {
            throw new RegraNegocioException("Combustível inválido");
        }
        if (carroTesteDrive.getPrecoInicial() == null || carroTesteDrive.getPrecoInicial() == 0) {
            throw new RegraNegocioException("Preço inicial inválido");
        }
        if (carroTesteDrive.getCarroceria() == null || carroTesteDrive.getCarroceria().getNome().isEmpty()) {
            throw new RegraNegocioException("Carroceria inválida");
        }
        if (carroTesteDrive.getFoiVendido() == null) {
            throw new RegraNegocioException("Condição foi vendido inválida");
        }

        // EXCLUSIVO TESTE DRIVE

        if (carroTesteDrive.getTestDrive().getId() == null || carroTesteDrive.getTestDrive().getId()  == 0) {
            throw new RegraNegocioException("ID do teste drive inválido");
        }

        if (carroTesteDrive.getTestDrive().getCliente() == null || carroTesteDrive.getTestDrive().getCliente().getId() == 0) {
            throw new RegraNegocioException("Cliente teste drive inválido");
        }

        if (carroTesteDrive.getTestDrive().getFuncionario() == null || carroTesteDrive.getTestDrive().getFuncionario().getId() == 0) {
            throw new RegraNegocioException("Funcionario teste drive inválido");
        }

    }
}
