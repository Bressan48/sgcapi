package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.EstadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstadoService {
    private EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> getEstados() {
        return repository.findAll();
    }

    public Optional<Estado> getEstadoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Estado salvar(Estado estado) {
        validar(estado);
        return repository.save(estado);
    }

    @Transactional
    public void excluir(Estado estado) {
        Objects.requireNonNull(estado.getId());
        repository.delete(estado);
    }

    public void validar(Estado estado) {
        if (estado.getNome() == null || estado.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome da estado inválida");
        }
        if (estado.getSigla() == null || estado.getSigla().isEmpty()) {
            throw new RegraNegocioException("Sigla inválida");
        }

    }
}
