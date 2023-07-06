package canto.minas.api.controller2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
//import med.voll.api.domain.loja.*;
import canto.minas.api.domain.repositor.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("repositor")
@CrossOrigin("*")
// @SecurityRequirement(name = "bearer-key")
public class RepositorController {

    @Autowired
    private RepositorRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar um novo Repositor", description = "Cria um novo Repositor com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Repositor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro do Repositor")
    })
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRepositor dados, UriComponentsBuilder uriBuilder) {
        var repositor = new Repositor(dados);
        repository.save(repositor);

        var uri = uriBuilder.path("/repositores/{id}").buildAndExpand(repositor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRepositor(repositor));
    }

    @GetMapping
    @Operation(summary = "Listar Repositores", description = "Retorna uma lista paginada de Repositores ativos.")
    @ApiResponse(responseCode = "200", description = "Lista de Repositores retornada com sucesso")
    public ResponseEntity<Page<DadosListagemRepositor>> listar(
            @PageableDefault(size = 100, sort = { "nome" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemRepositor::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar Repositor", description = "Atualiza as informações de um Repositor existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Repositor atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização do Repositor")
    })
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoRepositor dados) {
        var repositor = repository.getReferenceById(dados.id());
        repositor.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRepositor(repositor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir Repositor", description = "Exclui um Repositor existente.")
    @ApiResponse(responseCode = "204", description = "Repositor excluído com sucesso")
    public ResponseEntity excluir(@PathVariable Long id) {
        var repositor = repository.getReferenceById(id);
        repositor.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar Repositor", description = "Retorna os detalhes de um Repositor específico.")
    @ApiResponse(responseCode = "200", description = "Detalhes do Repositor retornados com sucesso")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var repositor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRepositor(repositor));
    }

}