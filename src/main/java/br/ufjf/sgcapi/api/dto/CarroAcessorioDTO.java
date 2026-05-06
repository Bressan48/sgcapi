package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.CarroAcessorio;
import br.ufjf.sgcapi.model.entity.TestDrive;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroAcessorioDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCarro;
    private String modeloCarro;

    private Long idAcessorio;
    private String nomeAcessorio;

    public static CarroAcessorioDTO create(CarroAcessorio carroAcessorio) {
        ModelMapper modelMapper = new ModelMapper();
        CarroAcessorioDTO dto = modelMapper.map(carroAcessorio, CarroAcessorioDTO.class);
        dto.modeloCarro = carroAcessorio.getCarro().getModelo().getNome();
        dto.nomeAcessorio = carroAcessorio.getAcessorio().getNome();
        return dto;
    }

}
