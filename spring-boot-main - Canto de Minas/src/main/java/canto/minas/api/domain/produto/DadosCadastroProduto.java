package canto.minas.api.domain.produto;

//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
//import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroProduto(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotBlank String foto,
        @NotBlank String codProd) {
}