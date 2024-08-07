package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class AlunoDto {

    //A proposta de colocar todos os atributos no DTO foi apenas para
    //facilitar a visualização do código e efetuar testes, mas não é uma boa prática.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @NotBlank
    @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$")
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Email
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "dtNascimento", nullable = false)
    private Date dataNascimento;

    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
    @Column(name = "celular", length = 15, nullable = false, unique = true)
    private String celular;

    @Column(name = "apelido", length = 255, nullable = true)
    private String apelido;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;



    public AlunoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AlunoModel.class);
    }
}
