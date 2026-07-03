package br.ufjf.sgcapi.api.controller;

import br.ufjf.sgcapi.api.dto.VendaDTO;
import br.ufjf.sgcapi.exception.RegraNegocioException;
import br.ufjf.sgcapi.model.entity.Cliente;
import br.ufjf.sgcapi.model.entity.Funcionario;
import br.ufjf.sgcapi.model.entity.Venda;
import br.ufjf.sgcapi.service.VendaService;
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
@RequestMapping("/api/v1/venda")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Vendas")
public class VendaController {

    private final VendaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Venda> lista = service.getVendas();
        return ResponseEntity.ok(lista.stream().map(VendaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Venda> obj = service.getVendaById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Venda não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj.map(VendaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody VendaDTO dto) {
        try {
            Venda obj = converter(dto);
            obj = service.salvar(obj);
            return new ResponseEntity(VendaDTO.create(obj), HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody VendaDTO dto) {
        if (!service.getVendaById(id).isPresent()) {
            return new ResponseEntity("Venda não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Venda obj = converter(dto);
            obj.setId(id);
            service.salvar(obj);
            return ResponseEntity.ok(VendaDTO.create(obj));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Venda> obj = service.getVendaById(id);
        if (!obj.isPresent()) {
            return new ResponseEntity("Venda não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(obj.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Venda converter(VendaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Venda venda = modelMapper.map(dto, Venda.class);

        if (dto.getIdCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getIdCliente());
            venda.setCliente(cliente);
        }

        if (dto.getIdFuncionario() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(dto.getIdFuncionario());
            venda.setFuncionario(funcionario);
        }

        return venda;
    }
}