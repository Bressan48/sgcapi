package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Carro;
import br.ufjf.sgcapi.model.entity.CarroTestDrive;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroTestDriveDTO extends Carro {

    private Long idCarro;
    private String nomeCarro;

    public static CarroTestDriveDTO create(CarroTestDrive carroTestDrive) {
        ModelMapper modelMapper = new ModelMapper();
        CarroTestDriveDTO dto = modelMapper.map(carroTestDrive, CarroTestDriveDTO.class);
        dto.nomeCarro = carroTestDrive.getModelo().getNome();
        dto.idCarro = carroTestDrive.getModelo().getId();
        return dto;
    }
}
