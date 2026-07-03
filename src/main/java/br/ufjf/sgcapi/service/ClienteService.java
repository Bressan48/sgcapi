package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {
    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> getClientes() {
        return repository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        validar(cliente);
        return repository.save(cliente);
    }

    @Transactional
    public void excluir(Cliente cliente) {
        Objects.requireNonNull(cliente.getId());
        repository.delete(cliente);
    }

    public void validar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do cliente inválido");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new RegraNegocioException("CPF do cliente inválido");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new RegraNegocioException("Email do cliente inválido");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
            throw new RegraNegocioException("Endereço do cliente inválido");
        }
        if (cliente.getNumTelefone() == null || cliente.getNumTelefone().isEmpty()) {
            throw new RegraNegocioException("Número de telefone do cliente inválido");
        }

    }
}
