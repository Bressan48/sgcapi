package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.CarroUsadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarroUsadoService {
    private CarroUsadoRepository repository;

    public CarroUsadoService(CarroUsadoRepository repository) {
        this.repository = repository;
    }

    public List<CarroUsado> getCarrosUsados() {
        return repository.findAll();
    }

    public Optional<CarroUsado> getCarroUsadoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public CarroUsado salvar(CarroUsado carroUsado) {
        validar(carroUsado);
        return repository.save(carroUsado);
    }

    @Transactional
    public void excluir(CarroUsado carroUsado) {
        Objects.requireNonNull(carroUsado.getId());
        repository.delete(carroUsado);
    }

    public void validar(CarroUsado carroUsado) {
        if (carroUsado.getModelo() == null || carroUsado.getModelo().getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do modelo inválida");
        }
        if (carroUsado.getId() == null || carroUsado.getModelo().getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }
        if (carroUsado.getDocEmDia() == null) {
            throw new RegraNegocioException("Documentão em dia inválida");
        }
        if (carroUsado.getDonoAnterior() == null|| carroUsado.getDonoAnterior().isEmpty()) {
            throw new RegraNegocioException("Dono anterior inválido");
        }
        if (carroUsado.getKmRodados() == null|| carroUsado.getKmRodados() == 0) {
            throw new RegraNegocioException("Dono anterior inválido");
        }
        if (carroUsado.getPrecoTabelaFipe() == null|| carroUsado.getPrecoTabelaFipe() == 0) {
            throw new RegraNegocioException("Dono anterior inválido");
        }
        if (carroUsado.getPlaca() == null || carroUsado.getPlaca().isEmpty()) {
            throw new RegraNegocioException("Placa inválida");
        }
        if (carroUsado.getChassi() == null || carroUsado.getChassi().isEmpty()) {
            throw new RegraNegocioException("Chassi inválido");
        }
        if (carroUsado.getCor() == null || carroUsado.getCor().isEmpty()) {
            throw new RegraNegocioException("Cor inválida");
        }

        if (carroUsado.getAnoFabricacao() == null || carroUsado.getAnoFabricacao() == 0) {
            throw new RegraNegocioException("Ano de Fabricação inválido");
        }
        if (carroUsado.getAnoModelo() == null || carroUsado.getAnoModelo() == 0) {
            throw new RegraNegocioException("Ano do Modelo inválido");
        }
        if (carroUsado.getCombustivel() == null || carroUsado.getCombustivel().getNome().isEmpty()) {
            throw new RegraNegocioException("Combustível inválido");
        }
        if (carroUsado.getPrecoInicial() == null || carroUsado.getPrecoInicial() == 0) {
            throw new RegraNegocioException("Preço inicial inválido");
        }
        if (carroUsado.getCarroceria() == null || carroUsado.getCarroceria().getNome().isEmpty()) {
            throw new RegraNegocioException("Carroceria inválida");
        }
        if (carroUsado.getFoiVendido() == null) {
            throw new RegraNegocioException("Condição foi vendido inválida");
        }

    }
}
