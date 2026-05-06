package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Combustivel;
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

public class CombustivelDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public static CombustivelDTO create(Combustivel combustivel) {
        ModelMapper modelMapper = new ModelMapper();
        CombustivelDTO dto = modelMapper.map(combustivel, CombustivelDTO.class);
        return dto;
    }

}
