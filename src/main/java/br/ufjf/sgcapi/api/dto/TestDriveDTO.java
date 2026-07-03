package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.TestDrive;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TestDriveDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCliente;
    private String nomeCliente;

    private Long idFuncionario;
    private String nomeFuncionario;

    public static TestDriveDTO create(TestDrive testDrive) {
        ModelMapper modelMapper = new ModelMapper();
        TestDriveDTO dto = modelMapper.map(testDrive, TestDriveDTO.class);
        dto.nomeCliente = testDrive.getCliente().getNome();
        dto.nomeFuncionario = testDrive.getFuncionario().getNome();
        return dto;
    }
}
