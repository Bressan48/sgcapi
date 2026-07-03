package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.CarroNovoDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.Carroceria;
import br.ufjf.sgcapi.model.entity.Combustivel;
import br.ufjf.sgcapi.model.entity.Modelo;
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
            return new ResponseEntity("Carro Novo não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(CarroNovoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CarroNovoDTO dto) {
        try {
            CarroNovo obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(CarroNovoDTO.create(obj), HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CarroNovoDTO dto) {
        if (!service.getCarroNovoById(id).isPresent()) {
            return new ResponseEntity("Carro Novo não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            CarroNovo obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(CarroNovoDTO.create(obj));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<CarroNovo> obj = service.getCarroNovoById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Carro Novo não encontrado", HttpStatus.NOT_FOUND);
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
        CarroNovo carroNovo = modelMapper.map(dto, CarroNovo.class);

        if (dto.getIdModelo() != null) {
            Modelo modelo = new Modelo();
            modelo.setId(dto.getIdModelo());
            modelo.setNome(dto.getNomeModelo());
            carroNovo.setModelo(modelo);
        }

        if (dto.getIdCombustivel() != null) {
            Combustivel combustivel = new Combustivel();
            combustivel.setId(dto.getIdCombustivel());
            combustivel.setNome(dto.getNomeCombustivel());
            carroNovo.setCombustivel(combustivel);
        }

        if (dto.getIdCarroceria() != null) {
            Carroceria carroceria = new Carroceria();
            carroceria.setId(dto.getIdCarroceria());
            carroceria.setNome(dto.getNomeCarroceria());
            carroNovo.setCarroceria(carroceria);
        }

        return carroNovo;
    }
}