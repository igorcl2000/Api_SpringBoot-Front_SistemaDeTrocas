package med.volli.api.funcionario;

import jakarta.validation.constraints.NotNull;
import med.volli.api.endereco.DadosEndereco;

public record DadosAtualizacaoFuncionario(
                @NotNull Long id,
                String nome,
                String telefone,
                DadosEndereco endereco) {
}