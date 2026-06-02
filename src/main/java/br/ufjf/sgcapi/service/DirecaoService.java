package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.DirecaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DirecaoService {
    private DirecaoRepository repository;

    public DirecaoService(DirecaoRepository repository) {
        this.repository = repository;
    }

    public List<Direcao> getDirecaos() {
        return repository.findAll();
    }

    public Optional<Direcao> getDirecaoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Direcao salvar(Direcao direcao) {
        validar(direcao);
        return repository.save(direcao);
    }

    @Transactional
    public void excluir(Direcao direcao) {
        Objects.requireNonNull(direcao.getId());
        repository.delete(direcao);
    }

    public void validar(Direcao direcao) {
        if (direcao.getId() == null || direcao.getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }
        if (direcao.getNome() == null || direcao.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome inválido");
        }

    }
}
