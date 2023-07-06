package canto.minas.api.controller2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
//import med.voll.api.domain.loja.*;
import canto.minas.api.domain.trocas.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("trocas")
@CrossOrigin("*")
// @SecurityRequirement(name = "bearer-key")
public class TrocaController {

    @Autowired
    private TrocaRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar uma nova troca", description = "Cria uma nova troca com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Troca cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro da troca")
    })
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTroca dados, UriComponentsBuilder uriBuilder) {
        var troca = new Troca(dados);
        repository.save(troca);

        var uri = uriBuilder.path("/trocas/{id}").buildAndExpand(troca.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTroca(troca));
    }

    @GetMapping
    @Operation(summary = "Listar trocas", description = "Retorna uma lista paginada de trocas ativas.")
    @ApiResponse(responseCode = "200", description = "Lista de trocas retornada com sucesso")
    public ResponseEntity<Page<DadosListagemTroca>> listar(
            @PageableDefault(size = 100, sort = { "dataTroca" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTroca::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar troca", description = "Atualiza as informações de uma troca existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Troca atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização da troca")
    })
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTroca dados) {
        var troca = repository.getReferenceById(dados.id());
        troca.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTroca(troca));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir troca", description = "Exclui uma troca existente.")
    @ApiResponse(responseCode = "204", description = "Troca excluída com sucesso")
    public ResponseEntity excluir(@PathVariable Long id) {
        var troca = repository.getReferenceById(id);
        troca.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar troca", description = "Retorna os detalhes de uma troca específica.")
    @ApiResponse(responseCode = "200", description = "Detalhes da troca retornados com sucesso")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var troca = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTroca(troca));
    }

}