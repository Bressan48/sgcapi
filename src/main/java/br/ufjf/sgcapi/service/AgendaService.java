package br.ufjf.sgcapi.service;

import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.*;
import br.ufjf.sgcapi.model.repository.AgendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgendaService {
    private AgendaRepository repository;

    public AgendaService(AgendaRepository repository) {
        this.repository = repository;
    }

    public List<Agenda> getAgendas() {
        return repository.findAll();
    }

    public Optional<Agenda> getAgendaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Agenda salvar(Agenda agenda) {
        validar(agenda);
        return repository.save(agenda);
    }

    @Transactional
    public void excluir(Agenda agenda) {
        Objects.requireNonNull(agenda.getId());
        repository.delete(agenda);
    }

    public void validar(Agenda agenda) {
        if (agenda.getId() == null || agenda.getId() == 0) {
            throw new RegraNegocioException("Id inválido");
        }
        if (agenda.getData() == null || agenda.getData().isEmpty()) {
            throw new RegraNegocioException("Data inválida");
        }
        if (agenda.getFoiAgendado() == null) {
            throw new RegraNegocioException("Id inválido");
        }
        if (agenda.getHorario() == null || agenda.getHorario().isEmpty()) {
            throw new RegraNegocioException("Horário inválido");
        }

    }
}
