package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.CarroceriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarroceriaService {
    private CarroceriaRepository repository;

    public CarroceriaService(CarroceriaRepository repository) {
        this.repository = repository;
    }

    public List<Carroceria> getCarrocerias() {
        return repository.findAll();
    }

    public Optional<Carroceria> getCarroceriaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Carroceria salvar(Carroceria carroceria) {
        validar(carroceria);
        return repository.save(carroceria);
    }

    @Transactional
    public void excluir(Carroceria carroceria) {
        Objects.requireNonNull(carroceria.getId());
        repository.delete(carroceria);
    }

    public void validar(Carroceria carroceria) {
        if (carroceria.getNome() == null || carroceria.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome da carroceria inválida");
        }

    }
}
