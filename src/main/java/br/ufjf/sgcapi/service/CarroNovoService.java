package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.CarroNovoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarroNovoService {
    private CarroNovoRepository repository;

    public CarroNovoService(CarroNovoRepository repository) {
        this.repository = repository;
    }

    public List<CarroNovo> getCarroNovos() {
        return repository.findAll();
    }

    public Optional<CarroNovo> getCarroNovoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public CarroNovo salvar(CarroNovo carroNovo) {
        validar(carroNovo);
        return repository.save(carroNovo);
    }

    @Transactional
    public void excluir(CarroNovo carroNovo) {
        Objects.requireNonNull(carroNovo.getId());

        if (repository.existsById(carroNovo.getId())) {
            repository.deleteById(carroNovo.getId());
        } else {
            throw new RegraNegocioException("Carro não encontrado para exclusão.");
        }
    }

    public void validar(CarroNovo carroNovo) {
        // Validação focada em garantir que o relacionamento/ID existe, e não o "nome" dele.
        if (carroNovo.getModelo() == null || carroNovo.getModelo().getId() == null) {
            throw new RegraNegocioException("Modelo inválido ou não informado");
        }
        if (carroNovo.getCombustivel() == null || carroNovo.getCombustivel().getId() == null) {
            throw new RegraNegocioException("Combustível inválido ou não informado");
        }
        if (carroNovo.getCarroceria() == null || carroNovo.getCarroceria().getId() == null) {
            throw new RegraNegocioException("Carroceria inválida ou não informada");
        }

        if (carroNovo.getPlaca() == null || carroNovo.getPlaca().trim().isEmpty()) {
            throw new RegraNegocioException("Placa inválida");
        }
        if (carroNovo.getChassi() == null || carroNovo.getChassi().trim().isEmpty()) {
            throw new RegraNegocioException("Chassi inválido");
        }
        if (carroNovo.getCor() == null || carroNovo.getCor().trim().isEmpty()) {
            throw new RegraNegocioException("Cor inválida");
        }
        if (carroNovo.getAnosDeGarantia() == null || carroNovo.getAnosDeGarantia() <= 0) {
            throw new RegraNegocioException("Anos de garantia inválidos");
        }
        if (carroNovo.getAnoFabricacao() == null || carroNovo.getAnoFabricacao() <= 0) {
            throw new RegraNegocioException("Ano de Fabricação inválido");
        }
        if (carroNovo.getAnoModelo() == null || carroNovo.getAnoModelo() <= 0) {
            throw new RegraNegocioException("Ano do Modelo inválido");
        }
        if (carroNovo.getPrecoInicial() == null || carroNovo.getPrecoInicial() <= 0) {
            throw new RegraNegocioException("Preço inicial inválido");
        }
    }
}