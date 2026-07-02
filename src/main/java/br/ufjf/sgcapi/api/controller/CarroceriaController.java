
package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.CarroceriaDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.Carroceria;
import br.ufjf.sgcapi.service.CarroceriaService;
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
@RequestMapping("/api/v1/carroceria")

@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Carroceria")
public class CarroceriaController {

    private final CarroceriaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Carroceria> lista = service.getCarrocerias();
        return ResponseEntity.ok(lista.stream().map(CarroceriaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Carroceria> obj = service.getCarroceriaById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(CarroceriaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CarroceriaDTO dto) {
        try {
            Carroceria obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CarroceriaDTO dto) {
        if (!service.getCarroceriaById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Carroceria obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Carroceria> obj = service.getCarroceriaById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Carroceria não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Carroceria converter(CarroceriaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Carroceria.class);
    }
}
