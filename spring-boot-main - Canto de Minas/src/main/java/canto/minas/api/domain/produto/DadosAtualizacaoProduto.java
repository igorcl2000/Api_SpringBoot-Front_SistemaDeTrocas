package canto.minas.api.domain.produto;

import jakarta.validation.constraints.NotNull;
//import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoProduto(
        @NotNull Long id,
        String nome,
        String descricao,
        String foto,
        String codProd) {
}
