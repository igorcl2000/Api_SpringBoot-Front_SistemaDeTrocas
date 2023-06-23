package med.voll.api.domain.repositor;

import jakarta.validation.constraints.NotNull;
//import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoRepositor(
                @NotNull Long id,
                String nome,
                String cpf,
                String email,
                String telefone) {
}
