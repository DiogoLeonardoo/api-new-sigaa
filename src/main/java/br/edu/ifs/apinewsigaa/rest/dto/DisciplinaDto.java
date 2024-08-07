package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class DisciplinaDto {

    @Column(name = "nome", length = 255, nullable = false, unique = true)
    private String nome;
    @Column(name = "numeroCreditos", nullable = false)
    private int numeroCreditos;

    public DisciplinaModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, DisciplinaModel.class);
    }
}
