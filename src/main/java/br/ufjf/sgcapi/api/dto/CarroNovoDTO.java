package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Carro;
import br.ufjf.sgcapi.model.entity.CarroNovo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarroNovoDTO extends Carro {

    private String placa;
    private Integer anosDeGarantia;

    private Long idModelo;
    private String nomeModelo;

    private Long idCombustivel;
    private String nomeCombustivel;

    private Long idCarroceria;
    private String nomeCarroceria;

    public static CarroNovoDTO create(CarroNovo carroNovo) {
        ModelMapper modelMapper = new ModelMapper();
        CarroNovoDTO dto = modelMapper.map(carroNovo, CarroNovoDTO.class);

        if (carroNovo.getModelo() != null) {
            dto.idModelo = carroNovo.getModelo().getId();
            dto.nomeModelo = carroNovo.getModelo().getNome();
        }
        if (carroNovo.getCombustivel() != null) {
            dto.idCombustivel = carroNovo.getCombustivel().getId();
            dto.nomeCombustivel = carroNovo.getCombustivel().getNome();
        }
        if (carroNovo.getCarroceria() != null) {
            dto.idCarroceria = carroNovo.getCarroceria().getId();
            dto.nomeCarroceria = carroNovo.getCarroceria().getNome();
        }
        return dto;
    }
}