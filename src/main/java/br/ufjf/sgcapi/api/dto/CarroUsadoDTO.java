package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Carro;
import br.ufjf.sgcapi.model.entity.CarroUsado;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroUsadoDTO extends Carro {

    private Float kmRodados;

    private String donoAnterior;

    private Boolean docEmDia;

    private Float precoTabelaFipe;

    private Long idCarro;
    private Long idModelo;
    private String nomeModelo;

    public static CarroUsadoDTO create(CarroUsado carroUsado) {
        ModelMapper modelMapper = new ModelMapper();
        CarroUsadoDTO dto = modelMapper.map(carroUsado, CarroUsadoDTO.class);
        dto.nomeModelo = carroUsado.getModelo().getNome();
        dto.idCarro = carroUsado.getModelo().getId();
        return dto;
    }

}
