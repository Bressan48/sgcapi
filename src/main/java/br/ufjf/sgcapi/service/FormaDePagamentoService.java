package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.FormaDePagamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FormaDePagamentoService {
    private FormaDePagamentoRepository repository;

    public FormaDePagamentoService(FormaDePagamentoRepository repository) {
        this.repository = repository;
    }

    public List<FormaDePagamento> getFormaDePagamentos() {
        return repository.findAll();
    }

    public Optional<FormaDePagamento> getFormaDePagamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public FormaDePagamento salvar(FormaDePagamento formaDePagamento) {
        validar(formaDePagamento);
        return repository.save(formaDePagamento);
    }

    @Transactional
    public void excluir(FormaDePagamento formaDePagamento) {
        Objects.requireNonNull(formaDePagamento.getId());
        repository.delete(formaDePagamento);
    }

    public void validar(FormaDePagamento formaDePagamento) {
        if (formaDePagamento.getFormaDePagamento() == null || formaDePagamento.getFormaDePagamento().isEmpty()) {
            throw new RegraNegocioException("Forma de pagamento inválida");
        }
        if (formaDePagamento.getTemJuros() == null ) {
            throw new RegraNegocioException("Juros inválido");
        }

    }
}
