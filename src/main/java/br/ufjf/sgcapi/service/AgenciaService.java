package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.AgenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgenciaService {
    private AgenciaRepository repository;

    public AgenciaService(AgenciaRepository repository) {
        this.repository = repository;
    }

    public List<Agencia> getAgencias() {
        return repository.findAll();
    }

    public Optional<Agencia> getAgenciaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Agencia salvar(Agencia agencia) {
        validar(agencia);
        return repository.save(agencia);
    }

    @Transactional
    public void excluir(Agencia agencia) {
        Objects.requireNonNull(agencia.getId());
        repository.delete(agencia);
    }

    public void validar(Agencia agencia) {
        if (agencia.getNome() == null || agencia.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome da agencia inválido");
        }
        if (agencia.getId() == null || agencia.getId() == 0) {
            throw new RegraNegocioException("Id da agencia inválido");
        }
        if (agencia.getEstado()== null || agencia.getEstado().getNome().isEmpty()) {
            throw new RegraNegocioException("Estado da agencia inválido");
        }
        if (agencia.getCidade()== null || agencia.getCidade().getNome().isEmpty()) {
            throw new RegraNegocioException("Cidade da agencia inválido");
        }
        if (agencia.getValorEmCaixa() == null){
            throw new RegraNegocioException("Valor em caixa da agencia inválido");
        }

    }
}
