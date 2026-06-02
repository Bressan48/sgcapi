package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.AcessorioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AcessorioService {
    private AcessorioRepository repository;

    public AcessorioService(AcessorioRepository repository) {
        this.repository = repository;
    }

    public List<Acessorio> getAcessorios() {
        return repository.findAll();
    }

    public Optional<Acessorio> getAcessorioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Acessorio salvar(Acessorio acessorio) {
        validar(acessorio);
        return repository.save(acessorio);
    }

    @Transactional
    public void excluir(Acessorio acessorio) {
        Objects.requireNonNull(acessorio.getId());
        repository.delete(acessorio);
    }

    public void validar(Acessorio acessorio) {
        if (acessorio.getNome() == null || acessorio.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do acessorio inválido");
        }
        if (acessorio.getId() == null || acessorio.getId() == 0) {
            throw new RegraNegocioException("Id do acessorio inválido");
        }

    }
}
