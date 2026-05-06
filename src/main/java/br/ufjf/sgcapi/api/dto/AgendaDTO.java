package br.ufjf.sgcapi.api.dto;

import br.ufjf.sgcapi.model.entity.Agenda;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String horario;
    private Boolean foiAgendado;

    public static AgendaDTO create(Agenda agenda) {
        ModelMapper modelMapper = new ModelMapper();
        AgendaDTO dto = modelMapper.map(agenda, AgendaDTO.class);
        return dto;
    }

}
