package canto.minas.api.domain.loja;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import med.voll.api.domain.endereco.DadosEndereco;
import canto.minas.api.domain.endereco.Endereco;

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
        this.responsavel = dados.responsavel();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoLoja dados) {
        if (dados.rasaoSocial() != null) {
            this.rasaoSocial = dados.rasaoSocial();
        }
        if (dados.nomeFantasia() != null) {
            this.nomeFantasia = dados.nomeFantasia();
        }
        if (dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.responsavel() != null) {
            this.responsavel = dados.responsavel();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
