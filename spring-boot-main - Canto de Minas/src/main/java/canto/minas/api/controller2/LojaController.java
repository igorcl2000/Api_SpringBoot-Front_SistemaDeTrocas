package canto.minas.api.controller2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import canto.minas.api.domain.loja.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("loja")
@CrossOrigin("*")
// @SecurityRequirement(name = "bearer-key")
public class LojaController {

    @Autowired
    private LojaRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar uma nova loja", description = "Cria uma nova loja com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loja cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro da loja")
    })
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLoja dados, UriComponentsBuilder uriBuilder) {
        var loja = new Loja(dados);
        repository.save(loja);

        var uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoLoja(loja));
    }

    @GetMapping
    @Operation(summary = "Listar lojas", description = "Retorna uma lista paginada de lojas ativas.")
    @ApiResponse(responseCode = "200", description = "Lista de lojas retornada com sucesso")
    public ResponseEntity<Page<DadosListagemLoja>> listar(
            @PageableDefault(size = 100, sort = { "nomeFantasia" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemLoja::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar loja", description = "Atualiza as informações de uma loja existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loja atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização da loja")
    })
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoLoja dados) {
        var loja = repository.getReferenceById(dados.id());
        loja.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoLoja(loja));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir loja", description = "Exclui uma loja existente.")
    @ApiResponse(responseCode = "204", description = "Loja excluída com sucesso")
    public ResponseEntity excluir(@PathVariable Long id) {
        var loja = repository.getReferenceById(id);
        loja.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar loja", description = "Retorna os detalhes de uma loja específica.")
    @ApiResponse(responseCode = "200", description = "Detalhes da loja retornados com sucesso")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var loja = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoLoja(loja));
    }

}