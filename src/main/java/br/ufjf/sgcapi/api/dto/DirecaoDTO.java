package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Direcao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DirecaoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public static DirecaoDTO create(Direcao diracao) {
        ModelMapper modelMapper = new ModelMapper();
        DirecaoDTO dto = modelMapper.map(diracao, DirecaoDTO.class);
        return dto;
    }

}
