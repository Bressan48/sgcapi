package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.ModeloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ModeloService{
    private ModeloRepository repository;

    public ModeloService(ModeloRepository repository) {
        this.repository = repository;
    }

    public List<Modelo> getModelos() {
        return repository.findAll();
    }

    public Optional<Modelo> getModeloById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Modelo salvar(Modelo modelo) {
        validar(modelo);
        return repository.save(modelo);
    }

    @Transactional
    public void excluir(Modelo modelo) {
        Objects.requireNonNull(modelo.getId());
        repository.delete(modelo);
    }

    public void validar(Modelo modelo) {
        if (modelo.getNome() == null || modelo.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do modelo inválido");
        }
        if (modelo.getId() == null || modelo.getId() == 0) {
            throw new RegraNegocioException("Id do modelo inválido");
        }

    }
}
