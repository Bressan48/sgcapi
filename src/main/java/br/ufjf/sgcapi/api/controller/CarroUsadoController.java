package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.CarroUsadoDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.CarroUsado;
import br.ufjf.sgcapi.service.CarroUsadoService;
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
@RequestMapping("/api/v1/carroUsado")

@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Carros Usados")
public class CarroUsadoController {

    private final CarroUsadoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<CarroUsado> lista = service.getCarrosUsados();
        return ResponseEntity.ok(lista.stream().map(CarroUsadoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<CarroUsado> obj = service.getCarroUsadoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(CarroUsadoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CarroUsadoDTO dto) {
        try {
            CarroUsado obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CarroUsadoDTO dto) {
        if (!service.getCarroUsadoById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            CarroUsado obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<CarroUsado> obj = service.getCarroUsadoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("CarroUsado não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public CarroUsado converter(CarroUsadoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, CarroUsado.class);
    }
}
