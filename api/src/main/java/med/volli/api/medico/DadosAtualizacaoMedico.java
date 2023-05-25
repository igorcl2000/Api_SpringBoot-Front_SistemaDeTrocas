package med.volli.api.medico;

import jakarta.validation.constraints.NotNull;
import med.volli.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}