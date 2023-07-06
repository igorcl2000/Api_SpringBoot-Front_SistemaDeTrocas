package med.voll.api.domain.trocas;

//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
//import med.voll.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTroca(
        @NotNull Long idLoja,
        @NotNull Long idRepositor,
        @NotNull Long idProduto,
        @NotBlank String lote,
        @NotBlank String dataTroca,
        @NotBlank String descricao,
        @NotBlank String quantidade,
        @NotBlank String dataFabricacao,
        @NotBlank String dataVencimento,
        @NotBlank String motivoTroca) {
}