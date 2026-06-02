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
        repository.delete(carroNovo);
    }

    public void validar(CarroNovo carroNovo) {
        if (carroNovo.getModelo() == null || carroNovo.getModelo().getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do modelo inválida");
        }
        if (carroNovo.getId() == null || carroNovo.getModelo().getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }
        if (carroNovo.getPlaca() == null || carroNovo.getPlaca().isEmpty()) {
            throw new RegraNegocioException("Placa inválida");
        }
        if (carroNovo.getChassi() == null || carroNovo.getChassi().isEmpty()) {
            throw new RegraNegocioException("Chassi inválido");
        }
        if (carroNovo.getCor() == null || carroNovo.getCor().isEmpty()) {
            throw new RegraNegocioException("Cor inválida");
        }

        if (carroNovo.getAnosDeGarantia() == null || carroNovo.getAnosDeGarantia() == 0) {
            throw new RegraNegocioException("Ano de garantia inválido");
        }

        if (carroNovo.getAnoFabricacao() == null || carroNovo.getAnoFabricacao() == 0) {
            throw new RegraNegocioException("Ano de Fabricação inválido");
        }
        if (carroNovo.getAnoModelo() == null || carroNovo.getAnoModelo() == 0) {
            throw new RegraNegocioException("Ano do Modelo inválido");
        }
        if (carroNovo.getCombustivel() == null || carroNovo.getCombustivel().getNome().isEmpty()) {
            throw new RegraNegocioException("Combustível inválido");
        }
        if (carroNovo.getPrecoInicial() == null || carroNovo.getPrecoInicial() == 0) {
            throw new RegraNegocioException("Preço inicial inválido");
        }
        if (carroNovo.getCarroceria() == null || carroNovo.getCarroceria().getNome().isEmpty()) {
            throw new RegraNegocioException("Carroceria inválida");
        }
        if (carroNovo.getFoiVendido() == null) {
            throw new RegraNegocioException("Condição foi vendido inválida");
        }

    }
}
