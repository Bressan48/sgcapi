package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.CarroNovo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarroNovoDTO {

    private Long id;
    private Integer anoModelo;
    private Integer anoFabricacao;
    private String chassi;
    private String cor;
    private String placa;
    private Double precoInicial;
    private Boolean foiVendido;
    private Integer anosDeGarantia;

    private Long idModelo;
    private Long idCombustivel;
    private Long idCarroceria;
    private Long idDirecao;


    // Só pro GET
    private String nomeModelo;
    private String nomeCombustivel;
    private String nomeCarroceria;

    public static CarroNovoDTO create(CarroNovo carroNovo) {
        ModelMapper modelMapper = new ModelMapper();
        CarroNovoDTO dto = modelMapper.map(carroNovo, CarroNovoDTO.class);

        if (carroNovo.getModelo() != null) {
            dto.setIdModelo(carroNovo.getModelo().getId());
            dto.setNomeModelo(carroNovo.getModelo().getNome());
        }
        if (carroNovo.getCombustivel() != null) {
            dto.setIdCombustivel(carroNovo.getCombustivel().getId());
            dto.setNomeCombustivel(carroNovo.getCombustivel().getNome());
        }
        if (carroNovo.getCarroceria() != null) {
            dto.setIdCarroceria(carroNovo.getCarroceria().getId());
            dto.setNomeCarroceria(carroNovo.getCarroceria().getNome());
        }

        return dto;
    }
}