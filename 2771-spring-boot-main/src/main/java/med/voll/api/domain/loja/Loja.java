package med.voll.api.domain.loja;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "loja")
@Entity(name = "Loja")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String rasaoSocial;
    String nomeFantasia;
    String cnpj;
    String telefone;
    String responsavel;
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Loja(DadosCadastroLoja dados) {
        this.ativo = true;
        this.rasaoSocial = dados.rasaoSocial();
        this.nomeFantasia = dados.nomeFantasia();
        this.cnpj = dados.cnpj();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoLoja dados) {
        if (dados.nomeFantasia() != null) {
            this.nomeFantasia = dados.nomeFantasia();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
