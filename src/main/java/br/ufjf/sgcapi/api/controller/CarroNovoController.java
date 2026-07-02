package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.CarroNovoDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.CarroNovo;
import br.ufjf.sgcapi.service.CarroNovoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carroNovo")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Carros Novos")
public class CarroNovoController {

    private final CarroNovoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<CarroNovo> lista = service.getCarroNovos();
        return ResponseEntity.ok(lista.stream().map(CarroNovoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<CarroNovo> obj = service.getCarroNovoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(CarroNovoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CarroNovoDTO dto) {
        try {
            CarroNovo obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CarroNovoDTO dto) {
        if (!service.getCarroNovoById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            CarroNovo obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<CarroNovo> obj = service.getCarroNovoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("CarroNovo não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public CarroNovo converter(CarroNovoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, CarroNovo.class);
    }
}