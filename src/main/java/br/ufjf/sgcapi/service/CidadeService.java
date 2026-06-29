package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.CidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CidadeService {
    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public List<Cidade> getCidades() {
        return repository.findAll();
    }

    public Optional<Cidade> getCidadeById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        validar(cidade);
        return repository.save(cidade);
    }

    @Transactional
    public void excluir(Cidade cidade) {
        Objects.requireNonNull(cidade.getId());
        repository.delete(cidade);
    }

    public void validar(Cidade cidade) {
        if (cidade.getNome() == null || cidade.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do modelo inválida");
        }

    }
}
