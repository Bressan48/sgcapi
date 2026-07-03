package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Agencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AgenciaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Long idEstado;
    private String nomeEstado;
    private String siglaEstado;

    private Long idCidade;
    private String nomeCidade;

    private String endereco;

    private Float valorEmCaixa;

    public static AgenciaDTO create(Agencia agencia) {
        ModelMapper modelMapper = new ModelMapper();
        AgenciaDTO dto = modelMapper.map(agencia, AgenciaDTO.class);
        dto.nomeEstado = agencia.getEstado().getNome();
        dto.siglaEstado = agencia.getEstado().getSigla();
        dto.nomeCidade = agencia.getCidade().getNome();
        return dto;
    }

}

