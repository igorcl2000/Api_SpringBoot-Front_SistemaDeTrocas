package med.voll.api.controller2;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.loja.*;
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
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLoja dados, UriComponentsBuilder uriBuilder) {
        var loja = new Loja(dados);
        repository.save(loja);

        var uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoLoja(loja));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemLoja>> listar(
            @PageableDefault(size = 10, sort = { "nomeFantasia" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemLoja::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoLoja dados) {
        var loja = repository.getReferenceById(dados.id());
        loja.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoLoja(loja));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var loja = repository.getReferenceById(id);
        loja.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var loja = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoLoja(loja));
    }

}