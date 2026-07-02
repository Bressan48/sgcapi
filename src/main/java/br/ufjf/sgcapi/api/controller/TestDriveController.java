package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.TestDriveDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.TestDrive;
import br.ufjf.sgcapi.service.TestDriveService;
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
@RequestMapping("/api/v1/testDrive")

@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Testes Drive")
public class TestDriveController {

    private final TestDriveService service;

    @GetMapping()
    public ResponseEntity get() {
        List<TestDrive> lista = service.getTestDrives();
        return ResponseEntity.ok(lista.stream().map(TestDriveDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<TestDrive> obj = service.getTestDriveById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(TestDriveDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody TestDriveDTO dto) {
        try {
            TestDrive obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(obj, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody TestDriveDTO dto) {
        if (!service.getTestDriveById(id).isPresent()) {
            return new ResponseEntity("Acessório não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            TestDrive obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(obj);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<TestDrive> obj = service.getTestDriveById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("TestDrive não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public TestDrive converter(TestDriveDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, TestDrive.class);
    }
}
