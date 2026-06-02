package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.ModeloDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.Modelo;
import br.ufjf.sgcapi.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/modelo")

@RequiredArgsConstructor
@CrossOrigin
public class ModeloController {

    private final ModeloService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Modelo> lista = service.getModelos();
        return ResponseEntity.ok(lista.stream().map(ModeloDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Modelo> obj = service.getModeloById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(ModeloDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ModeloDTO dto) {
        try {
            Modelo obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ModeloDTO dto) {
        if (!service.getModeloById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Modelo obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Modelo> obj = service.getModeloById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Modelo não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Modelo converter(ModeloDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Modelo.class);
    }
}
