/*
package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.CarroAcessorioDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.CarroAcessorio;
import br.ufjf.sgcapi.service.CarroAcessorioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carroAcessorio") */
/* MUDE AQUI *//*

@RequiredArgsConstructor
@CrossOrigin
public class CarroAcessorioController {

    private final CarroAcessorioService service;

    @GetMapping()
    public ResponseEntity get() {
        List<CarroAcessorio> lista = service.getCarroAcessorios();
        return ResponseEntity.ok(lista.stream().map(CarroAcessorioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<CarroAcessorio> obj = service.getCarroAcessorioById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(CarroAcessorioDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CarroAcessorioDTO dto) {
        try {
            CarroAcessorio obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CarroAcessorioDTO dto) {
        if (!service.getCarroAcessorioById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            CarroAcessorio obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<CarroAcessorio> obj = service.getCarroAcessorioById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("CarroAcessorio não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public CarroAcessorio converter(CarroAcessorioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, CarroAcessorio.class);
    }
}
*/
