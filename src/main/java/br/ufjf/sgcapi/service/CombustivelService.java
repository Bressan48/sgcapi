package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.CombustivelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CombustivelService {
    private CombustivelRepository repository;

    public CombustivelService(CombustivelRepository repository) {
        this.repository = repository;
    }

    public List<Combustivel> getCombustivels() {
        return repository.findAll();
    }

    public Optional<Combustivel> getCombustivelById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Combustivel salvar(Combustivel combustivel) {
        validar(combustivel);
        return repository.save(combustivel);
    }

    @Transactional
    public void excluir(Combustivel combustivel) {
        Objects.requireNonNull(combustivel.getId());
        repository.delete(combustivel);
    }

    public void validar(Combustivel combustivel) {
        if (combustivel.getNome() == null || combustivel.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do modelo inválida");
        }
        if (combustivel.getId() == null || combustivel.getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }

    }
}
