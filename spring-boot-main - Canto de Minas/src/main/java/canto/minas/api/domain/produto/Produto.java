package canto.minas.api.domain.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import med.voll.api.domain.endereco.DadosEndereco;
//import med.voll.api.domain.endereco.Endereco;

@Table(name = "produto")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String descricao;
    String foto;
    String codProd;

    private Boolean ativo;

    public Produto(DadosCadastroProduto dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.foto = dados.foto();
        this.codProd = dados.codProd();
    }

    public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.foto() != null) {
            this.foto = dados.foto();
        }
        if (dados.codProd() != null) {
            this.codProd = dados.codProd();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
