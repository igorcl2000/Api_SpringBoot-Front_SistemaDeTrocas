package canto.minas.api.domain.loja;

import jakarta.validation.constraints.NotNull;
import canto.minas.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoLoja(
        @NotNull Long id,
        String rasaoSocial,
        String nomeFantasia,
        String cnpj,
        String telefone,
        String responsavel,
        DadosEndereco endereco) {
}
