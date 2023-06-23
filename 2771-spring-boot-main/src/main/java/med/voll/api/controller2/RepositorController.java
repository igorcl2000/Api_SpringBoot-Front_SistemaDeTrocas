package med.voll.api.controller2;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
//import med.voll.api.domain.loja.*;
import med.voll.api.domain.repositor.*;

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
@SecurityRequirement(name = "bearer-key")
public class RepositorController {

    @Autowired
    private RepositorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRepositor dados, UriComponentsBuilder uriBuilder) {
        var repositor = new Repositor(dados);
        repository.save(repositor);

        var uri = uriBuilder.path("/repositores/{id}").buildAndExpand(repositor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRepositor(repositor));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemRepositor>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemRepositor::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoRepositor dados) {
        var repositor = repository.getReferenceById(dados.id());
        repositor.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRepositor(repositor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var repositor = repository.getReferenceById(id);
        repositor.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var repositor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRepositor(repositor));
    }

}