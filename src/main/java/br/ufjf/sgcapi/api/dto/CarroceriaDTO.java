package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Carroceria;
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

public class CarroceriaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public static CarroceriaDTO create(Carroceria carroceria) {
        ModelMapper modelMapper = new ModelMapper();
        CarroceriaDTO dto = modelMapper.map(carroceria, CarroceriaDTO.class);
        return dto;
    }

}
