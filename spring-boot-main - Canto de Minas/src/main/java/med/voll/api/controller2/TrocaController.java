package med.voll.api.controller2;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
//import med.voll.api.domain.loja.*;
import med.voll.api.domain.trocas.*;

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
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTroca dados, UriComponentsBuilder uriBuilder) {
        var troca = new Troca(dados);
        repository.save(troca);

        var uri = uriBuilder.path("/trocas/{id}").buildAndExpand(troca.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTroca(troca));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTroca>> listar(
            @PageableDefault(size = 10, sort = { "dataTroca" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTroca::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTroca dados) {
        var troca = repository.getReferenceById(dados.id());
        troca.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTroca(troca));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var troca = repository.getReferenceById(id);
        troca.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var troca = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTroca(troca));
    }

}