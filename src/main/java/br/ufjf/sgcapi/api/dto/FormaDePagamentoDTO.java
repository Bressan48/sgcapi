package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.FormaDePagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FormaDePagamentoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formaDePagamento;
    private Boolean temJuros;

    public static FormaDePagamentoDTO create(FormaDePagamento formaDePagamento) {
        ModelMapper modelMapper = new ModelMapper();
        FormaDePagamentoDTO dto = modelMapper.map(formaDePagamento, FormaDePagamentoDTO.class);
        return dto;
    }

}
