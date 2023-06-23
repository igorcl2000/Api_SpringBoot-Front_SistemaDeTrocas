package med.voll.api.domain.loja;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoLoja(
                @NotNull Long id,
                String rasaoSocial,
                String nomeFantasia,
                String cnpj,
                String telefone,
                String responsavel,
                DadosEndereco endereco) {
}
