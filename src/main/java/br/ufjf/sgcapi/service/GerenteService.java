package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.GerenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GerenteService {
    private GerenteRepository repository;

    public GerenteService(GerenteRepository repository) {
        this.repository = repository;
    }

    public List<Gerente> getGerentes() {
        return repository.findAll();
    }

    public Optional<Gerente> getGerenteById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Gerente salvar(Gerente gerente) {
        validar(gerente);
        return repository.save(gerente);
    }

    @Transactional
    public void excluir(Gerente gerente) {
        Objects.requireNonNull(gerente.getId());
        repository.delete(gerente);
    }

    public void validar(Gerente gerente) {
        if (gerente.getNome() == null || gerente.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome da gerente inválida");
        }
        if (gerente.getEmail() == null || gerente.getEmail().isEmpty()) {
            throw new RegraNegocioException("Email inválido");
        }
        if (gerente.getSenha() == null || gerente.getSenha().isEmpty()) {
            throw new RegraNegocioException("Senha inválido");
        }
        if (gerente.getCpf() == null || gerente.getCpf().isEmpty()) {
            throw new RegraNegocioException("CPF inválido");
        }
        if (gerente.getNumTelefone() == null || gerente.getNumTelefone().isEmpty()) {
            throw new RegraNegocioException("Número de telefone inválido");
        }
        if (gerente.getEndereco() == null || gerente.getEndereco().isEmpty()) {
            throw new RegraNegocioException("Endereço inválido");
        }

    }
}
