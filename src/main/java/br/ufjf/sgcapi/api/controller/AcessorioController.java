
package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.AcessorioDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.Acessorio;
import br.ufjf.sgcapi.service.AcessorioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/acessorios")
@RequiredArgsConstructor
@CrossOrigin
public class AcessorioController {

    private final AcessorioService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Acessorio> lista = service.getAcessorios();
        return ResponseEntity.ok(lista.stream().map(AcessorioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Acessorio> obj = service.getAcessorioById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(AcessorioDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody AcessorioDTO dto) {
        try {
            Acessorio obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AcessorioDTO dto) {
        if (!service.getAcessorioById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Acessorio obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Acessorio> obj = service.getAcessorioById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Acessorio converter(AcessorioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Acessorio.class);
    }
}

