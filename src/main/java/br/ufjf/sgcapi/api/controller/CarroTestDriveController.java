package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.CarroTestDriveDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.CarroTestDrive;
import br.ufjf.sgcapi.service.CarroTestDriveService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carroTestDrive") /* MUDE AQUI */
@RequiredArgsConstructor
@CrossOrigin
public class CarroTestDriveController {

    private final CarroTestDriveService service;

    @GetMapping()
    public ResponseEntity get() {
        List<CarroTestDrive> lista = service.getCarroTestDrives();
        return ResponseEntity.ok(lista.stream().map(CarroTestDriveDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<CarroTestDrive> obj = service.getCarroTestDriveById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(CarroTestDriveDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CarroTestDriveDTO dto) {
        try {
            CarroTestDrive obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CarroTestDriveDTO dto) {
        if (!service.getCarroTestDriveById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            CarroTestDrive obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<CarroTestDrive> obj = service.getCarroTestDriveById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("CarroTestDrive não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public CarroTestDrive converter(CarroTestDriveDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, CarroTestDrive.class);
    }
}
