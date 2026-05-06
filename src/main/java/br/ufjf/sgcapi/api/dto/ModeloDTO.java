package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Modelo;
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

public class ModeloDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    public static ModeloDTO create(Modelo modelo) {
        ModelMapper modelMapper = new ModelMapper();
        ModeloDTO dto = modelMapper.map(modelo, ModeloDTO.class);
        return dto;
    }

}
