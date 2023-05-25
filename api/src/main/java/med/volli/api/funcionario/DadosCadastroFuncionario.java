package med.volli.api.funcionario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.volli.api.endereco.DadosEndereco;

public record DadosCadastroFuncionario(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,

        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
        @NotNull Especialidade especialidade,

        @NotNull @Valid DadosEndereco endereco) {
}