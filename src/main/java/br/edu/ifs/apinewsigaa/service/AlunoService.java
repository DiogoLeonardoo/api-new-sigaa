package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.AlunoRepository;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional(readOnly = true)
    public AlunoDto ObterPorMatricula(String matricula) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByMatricula(matricula);
        AlunoModel alunoModel = alunoOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));

        return alunoModel.toDTO();
    }
    

    @Transactional(readOnly = true)
    public List<AlunoDto> ObterTodos() {
        List<AlunoModel> alunoList = alunoRepository.findAll();

        return alunoList.stream()
                .map(aluno -> aluno.toDTO())
                .collect(Collectors.toList());
    }

     @Transactional
    public AlunoDto salvar (AlunoModel novoAluno){
        try {
            if(alunoRepository.existsByMatricula(novoAluno.getMatricula()) || alunoRepository.existsByCpf(novoAluno.getCpf()))
                   {
                throw new DataIntegrityException("Já existe um aluno cadastrado com o CPF ou matríula fornecida.");
            }
            return alunoRepository.save(novoAluno).toDTO();

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao criar um novo aluno.");
        }
    }

    @Transactional
    public AlunoDto atualizar (AlunoModel alunoExistente){
        try {
            if (!alunoRepository.existsById(alunoExistente.getId())){
                throw new ObjectNotFoundException("Aluno não encontrado com o ID: " + alunoExistente.getId());
            }
            return alunoRepository.save(alunoExistente).toDTO();

        } catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao atualizar um aluno.");
        }
    }

        //Delete somente com o ID

    @Transactional
    public void deletar(int id){
        try {
            if (!alunoRepository.existsById(id)){
                throw new ObjectNotFoundException("Aluno não encontrado com o ID: " + id);
            }
            alunoRepository.deleteById(id);

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao deletar um aluno.");
        }
    }

    //Delete com a matrícula
    @Transactional
    public void deleteByMatricula(String matricula){
        try {
            if (!alunoRepository.existsByMatricula(matricula)){
                throw new ObjectNotFoundException("Aluno não encontrado com a matrícula: " + matricula);
            }
            alunoRepository.deleteByMatricula(matricula);

        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao deletar um aluno.");
        }
    }
}
