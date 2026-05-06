package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Cliente;
import br.ufjf.sgcapi.model.entity.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;
    private String email;
    private String cpf;
    private String endereco;
    private String numTelefone;

    private String nome;

    public static ClienteDTO create(Cliente cliente) {
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
        dto.nome = cliente.getNome();
        return dto;
    }
}
