package canto.minas.api.domain.loja;

import jakarta.validation.Valid;
//import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
import canto.minas.api.domain.endereco.DadosEndereco;

public record DadosCadastroLoja(
                @NotBlank String nomeFantasia,
                @NotBlank String rasaoSocial,
                @NotBlank String cnpj,
                @NotBlank String telefone,
                @NotBlank String responsavel,

                @NotNull @Valid DadosEndereco endereco) {

}