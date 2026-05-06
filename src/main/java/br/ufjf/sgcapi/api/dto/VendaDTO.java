package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.FormaDePagamento;
import br.ufjf.sgcapi.model.entity.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class VendaDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float valorVenda;

    @ManyToOne
    private FormaDePagamento formaDePagamento;


    private String nomeCliente;
    private String nomeFuncionario;
    private Long idFuncionario;


    public static VendaDTO create(Venda venda) {
        ModelMapper modelMapper = new ModelMapper();
        VendaDTO dto = modelMapper.map(venda, VendaDTO.class);
        dto.nomeCliente = venda.getCliente().getNome();
        dto.nomeFuncionario = venda.getFuncionario().getNome();
        dto.idFuncionario = venda.getFuncionario().getId();
        return dto;
    }

}
