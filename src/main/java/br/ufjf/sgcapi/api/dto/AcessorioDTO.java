package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Acessorio;

import br.ufjf.sgcapi.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcessorioDTO {

    private Long id;
    private String nome;

    public static AcessorioDTO create(Acessorio acessorio) {
        ModelMapper modelMapper = new ModelMapper();
        AcessorioDTO dto = modelMapper.map(acessorio, AcessorioDTO.class);
        return dto;
    }
}
