package br.edu.ifs.apinewsigaa.rest.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class ProfessorDto {

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;
    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;
    @Column(name = "celular", length = 14, nullable = false, unique = true)
    private String celular;
    @Column(name = "matricula", nullable = false, unique = true)
    private int matricula;

    public ProfessorDto toDTO(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProfessorDto.class);
    }
}
