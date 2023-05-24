package med.voli.api.medico;

import med.volli.api.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade,
                DadosEndereco endereco) {
}