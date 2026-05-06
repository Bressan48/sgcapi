package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Gerente;
import br.ufjf.sgcapi.model.entity.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GerenteDTO {

    private Long id;
    private String email;
    private String senha;
    private String cpf;
    private String endereco;
    private String numTelefone;

    private String nome;

    public static GerenteDTO create(Gerente gerente) {
        ModelMapper modelMapper = new ModelMapper();
        GerenteDTO dto = modelMapper.map(gerente, GerenteDTO.class);
        dto.nome = gerente.getNome();
        return dto;
    }
}
