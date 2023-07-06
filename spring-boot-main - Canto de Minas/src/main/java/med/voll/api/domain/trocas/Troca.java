package med.voll.api.domain.trocas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "trocas")
@Entity(name = "Troca")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Troca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "nome_fantasia")
    // private Loja loja;
    // private String nomeLoja;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "nome")
    // private Repositor repositor;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "nome")
    // private Produto produto;

    private Long idLoja;
    private Long idRepositor;
    private Long idProduto;

    private String lote;
    private String dataTroca;
    private String descricao;
    private String quantidade;
    private String dataFabricacao;
    private String dataVencimento;
    private String motivoTroca;

    private Boolean ativo;

    // this.nomeLoja = loja.getNomeFantasia();

    public Troca(DadosCadastroTroca dados) {
        this.ativo = true;
        this.idLoja = dados.idLoja();
        this.idRepositor = dados.idRepositor();
        this.idProduto = dados.idProduto();
        this.lote = dados.lote();
        this.dataTroca = dados.dataTroca();
        this.descricao = dados.descricao();
        this.quantidade = dados.quantidade();
        this.dataFabricacao = dados.dataFabricacao();
        this.dataVencimento = dados.dataVencimento();
        this.motivoTroca = dados.motivoTroca();
    }

    public void atualizarInformacoes(DadosAtualizacaoTroca dados) {
        if (dados.idLoja() != null) {
            this.idLoja = dados.idLoja();
        }
        if (dados.idRepositor() != null) {
            this.idRepositor = dados.idRepositor();
        }
        if (dados.idProduto() != null) {
            this.idProduto = dados.idProduto();
        }
        if (dados.lote() != null) {
            this.lote = dados.lote();
        }
        if (dados.dataTroca() != null) {
            this.dataTroca = dados.dataTroca();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.quantidade() != null) {
            this.quantidade = dados.quantidade();
        }
        if (dados.dataFabricacao() != null) {
            this.dataFabricacao = dados.dataFabricacao();
        }
        if (dados.dataVencimento() != null) {
            this.dataVencimento = dados.dataVencimento();
        }
        if (dados.motivoTroca() != null) {
            this.motivoTroca = dados.motivoTroca();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
