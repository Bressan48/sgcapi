package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendaService {
    private VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public List<Venda> getVendas() {
        return repository.findAll();
    }

    public Optional<Venda> getVendaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Venda salvar(Venda venda) {
        validar(venda);
        return repository.save(venda);
    }

    @Transactional
    public void excluir(Venda venda) {
        Objects.requireNonNull(venda.getId());
        repository.delete(venda);
    }

    public void validar(Venda venda) {
        if (venda.getId() == null || venda.getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }
        if (venda.getValorVenda() == null || venda.getValorVenda() == 0) {
            throw new RegraNegocioException("Valor da venda inválido");
        }
        if (venda.getCliente() == null || venda.getCliente().getNome().isEmpty()) {
            throw new RegraNegocioException("Cliente inválida");
        }
        if (venda.getFuncionario().getId() == null || venda.getFuncionario().getId() == 0) {
            throw new RegraNegocioException("Funcionário inválida");
        }
        if (venda.getFormaDePagamento() == null || venda.getFormaDePagamento().getId() == 0) {
            throw new RegraNegocioException("Forma de pagamento inválida");
        }
    }
}
