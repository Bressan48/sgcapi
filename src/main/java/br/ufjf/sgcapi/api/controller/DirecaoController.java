package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.DirecaoDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.Direcao;
import br.ufjf.sgcapi.service.DirecaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/direcao")

@RequiredArgsConstructor
@CrossOrigin
public class DirecaoController {

    private final DirecaoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Direcao> lista = service.getDirecaos();
        return ResponseEntity.ok(lista.stream().map(DirecaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Direcao> obj = service.getDirecaoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(DirecaoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody DirecaoDTO dto) {
        try {
            Direcao obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody DirecaoDTO dto) {
        if (!service.getDirecaoById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Direcao obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Direcao> obj = service.getDirecaoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Direcao não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Direcao converter(DirecaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Direcao.class);
    }
}
