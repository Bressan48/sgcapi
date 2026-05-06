package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Carro;
import br.ufjf.sgcapi.model.entity.CarroNovo;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroNovoDTO extends Carro {

    private String placa;
    private Integer anosDeGarantia;


    private Long idCarro;
    private String modelo;

    public static CarroNovoDTO create(CarroNovo carroNovo) {
        ModelMapper modelMapper = new ModelMapper();
        CarroNovoDTO dto = modelMapper.map(carroNovo, CarroNovoDTO.class);
        dto.modelo = carroNovo.getModelo().getNome();
        dto.idCarro = carroNovo.getModelo().getId();
        return dto;
    }

}
