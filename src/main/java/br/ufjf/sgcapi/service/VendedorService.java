package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.VendedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendedorService {
    private VendedorRepository repository;

    public VendedorService(VendedorRepository repository) {
        this.repository = repository;
    }

    public List<Vendedor> getVendedors() {
        return repository.findAll();
    }

    public Optional<Vendedor> getVendedorById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Vendedor salvar(Vendedor vendedor) {
        validar(vendedor);
        return repository.save(vendedor);
    }

    @Transactional
    public void excluir(Vendedor vendedor) {
        Objects.requireNonNull(vendedor.getId());
        repository.delete(vendedor);
    }

    public void validar(Vendedor vendedor) {
        if (vendedor.getNome() == null || vendedor.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome da vendedor inválida");
        }
        if (vendedor.getId() == null || vendedor.getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }
        if (vendedor.getEmail() == null || vendedor.getEmail().isEmpty()) {
            throw new RegraNegocioException("Email inválido");
        }
        if (vendedor.getSenha() == null || vendedor.getSenha().isEmpty()) {
            throw new RegraNegocioException("Senha inválido");
        }
        if (vendedor.getCpf() == null || vendedor.getCpf().isEmpty()) {
            throw new RegraNegocioException("CPF inválido");
        }
        if (vendedor.getNumTelefone() == null || vendedor.getNumTelefone().isEmpty()) {
            throw new RegraNegocioException("Número de telefone inválido");
        }
        if (vendedor.getEndereco() == null || vendedor.getEndereco().isEmpty()) {
            throw new RegraNegocioException("Endereço inválido");
        }

    }
}
