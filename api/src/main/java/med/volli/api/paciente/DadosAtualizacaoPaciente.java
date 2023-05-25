package med.volli.api.paciente;

import jakarta.validation.Valid;
import med.volli.api.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco) {
}
