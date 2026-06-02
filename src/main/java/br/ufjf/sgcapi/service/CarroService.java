package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.CarroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarroService {
    private CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public List<Carro> getCarros() {
        return repository.findAll();
    }

    public Optional<Carro> getCarroById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Carro salvar(Carro carro) {
        validar(carro);
        return repository.save(carro);
    }

    @Transactional
    public void excluir(Carro carro) {
        Objects.requireNonNull(carro.getId());
        repository.delete(carro);
    }

    public void validar(Carro carro) {
        if (carro.getModelo() == null || carro.getModelo().getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do modelo inválida");
        }
        if (carro.getId() == null || carro.getModelo().getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }

    }
}
