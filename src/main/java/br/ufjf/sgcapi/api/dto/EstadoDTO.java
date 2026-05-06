package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Estado;
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

public class EstadoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sigla;
    public static EstadoDTO create(Estado estado) {
        ModelMapper modelMapper = new ModelMapper();
        EstadoDTO dto = modelMapper.map(estado, EstadoDTO.class);
        return dto;
    }

}

