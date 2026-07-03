package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.FormaDePagamento;
import br.ufjf.sgcapi.model.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {

    private Long id;
    private Float valorVenda;
    private FormaDePagamento formaDePagamento;

    private Long idCliente;
    private String nomeCliente;
    private Long idFuncionario;
    private String nomeFuncionario;

    public static VendaDTO create(Venda venda) {
        ModelMapper modelMapper = new ModelMapper();
        VendaDTO dto = modelMapper.map(venda, VendaDTO.class);

        if (venda.getCliente() != null) {
            dto.idCliente = venda.getCliente().getId();
            dto.nomeCliente = venda.getCliente().getNome();
        }
        if (venda.getFuncionario() != null) {
            dto.idFuncionario = venda.getFuncionario().getId();
            dto.nomeFuncionario = venda.getFuncionario().getNome();
        }
        return dto;
    }
}