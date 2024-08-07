package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.AlunoRepository;
import br.edu.ifs.apinewsigaa.rest.dto.ApiResponse;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import br.edu.ifs.apinewsigaa.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AlunoDto>>> ObterTodos() {
        List<AlunoDto> alunoDtoList = alunoService.ObterTodos();
        String message = alunoDtoList.isEmpty() ? "Nenhum aluno encontrado!" : "Alunos encontrados com sucesso!";
        ApiResponse<List<AlunoDto>> response = new ApiResponse<>(UUID.randomUUID().toString(), "Alunos encontrados com sucesso!", alunoDtoList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AlunoDto>> ObterPorId(@PathVariable("id") int id) {
        AlunoModel alunoModel = alunoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado com o ID: " + id));
        AlunoDto alunoDto = alunoModel.toDTO();
        ApiResponse<AlunoDto> response = new ApiResponse<>(UUID.randomUUID().toString(), "Aluno encontrado com sucesso!", alunoDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByMat/{mat}")
    public ResponseEntity<ApiResponse<AlunoDto>> ObterPorMatricula(@PathVariable("mat") String matricula) {
        AlunoDto alunoDto = alunoService.ObterPorMatricula(matricula);
        ApiResponse<AlunoDto> response = new ApiResponse<>(UUID.randomUUID().toString(), "Aluno encontrado com sucesso!", alunoDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AlunoDto>> Salvar(@RequestBody @Valid AlunoModel novoAluno) {
        AlunoDto alunoDto = alunoService.salvar(novoAluno);
        ApiResponse<AlunoDto> response = new ApiResponse<>(UUID.randomUUID().toString(), "Aluno criado com sucesso!", alunoDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<AlunoDto>> Atualizar(@RequestBody @Valid AlunoModel alunoExistente) {
        AlunoDto alunoDto = alunoService.atualizar(alunoExistente);
        ApiResponse<AlunoDto> response = new ApiResponse<>(UUID.randomUUID().toString(), "Aluno atualizado com sucesso!", alunoDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletar(@PathVariable int id) {
        alunoService.deletar(id);
        ApiResponse<Void> response = new ApiResponse<>(UUID.randomUUID().toString(), "Aluno deletado com sucesso!", null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletar/{matricula}")
    public ResponseEntity<ApiResponse<Void>> deleteByMatricula(@PathVariable String matricula) {
        alunoService.deleteByMatricula(matricula);
        ApiResponse<Void> response = new ApiResponse<>(UUID.randomUUID().toString(), "Aluno deletado com sucesso!", null);
        return ResponseEntity.ok(response);
    }
}