
package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.FormaDePagamentoDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.FormaDePagamento;
import br.ufjf.sgcapi.service.FormaDePagamentoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/formaDePagamento")

@RequiredArgsConstructor
@CrossOrigin
public class FormaDePagamentoController {

    private final FormaDePagamentoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<FormaDePagamento> lista = service.getFormaDePagamentos();
        return ResponseEntity.ok(lista.stream().map(FormaDePagamentoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<FormaDePagamento> obj = service.getFormaDePagamentoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(FormaDePagamentoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody FormaDePagamentoDTO dto) {
        try {
            FormaDePagamento obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FormaDePagamentoDTO dto) {
        if (!service.getFormaDePagamentoById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            FormaDePagamento obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<FormaDePagamento> obj = service.getFormaDePagamentoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("FormaDePagamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public FormaDePagamento converter(FormaDePagamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, FormaDePagamento.class);
    }
}
