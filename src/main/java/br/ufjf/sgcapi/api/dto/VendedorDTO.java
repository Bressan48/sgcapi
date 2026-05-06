package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Vendedor;
import br.ufjf.sgcapi.model.entity.Funcionario;
import br.ufjf.sgcapi.model.entity.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorDTO {

    private Long id;
    private String email;
    private String senha;
    private String cpf;
    private String endereco;
    private String numTelefone;

    private String nome;

    public static VendedorDTO create(Vendedor vendedor) {
        ModelMapper modelMapper = new ModelMapper();
        VendedorDTO dto = modelMapper.map(vendedor, VendedorDTO.class);
        dto.nome = vendedor.getNome();
        return dto;
    }
}
