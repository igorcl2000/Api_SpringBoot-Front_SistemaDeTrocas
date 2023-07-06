package med.voll.api.domain.repositor;

//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
//import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroRepositor(
                @NotBlank String nome,
                @NotBlank String cpf,
                @NotBlank String email,
                @NotBlank String telefone) {
}