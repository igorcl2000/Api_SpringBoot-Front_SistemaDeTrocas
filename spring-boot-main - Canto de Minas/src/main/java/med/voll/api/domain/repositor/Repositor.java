package med.voll.api.domain.repositor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import med.voll.api.domain.endereco.DadosEndereco;
//import med.voll.api.domain.endereco.Endereco;

@Table(name = "repositor")
@Entity(name = "Repositor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Repositor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String cpf;
    String email;
    String telefone;

    private Boolean ativo;

    public Repositor(DadosCadastroRepositor dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
    }

    public void atualizarInformacoes(DadosAtualizacaoRepositor dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
