/*
package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.CombustivelDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.Combustivel;
import br.ufjf.sgcapi.service.CombustivelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/combustivel") */
/* MUDE AQUI *//*

@RequiredArgsConstructor
@CrossOrigin
public class CombustivelController {

    private final CombustivelService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Combustivel> lista = service.getCombustivels();
        return ResponseEntity.ok(lista.stream().map(CombustivelDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Combustivel> obj = service.getCombustivelById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(CombustivelDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CombustivelDTO dto) {
        try {
            Combustivel obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CombustivelDTO dto) {
        if (!service.getCombustivelById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Combustivel obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Combustivel> obj = service.getCombustivelById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Combustivel não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Combustivel converter(CombustivelDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Combustivel.class);
    }
}
*/
