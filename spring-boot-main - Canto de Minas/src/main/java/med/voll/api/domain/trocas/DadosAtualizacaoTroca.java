package med.voll.api.domain.trocas;

//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoTroca(
                @NotNull Long id,
                Long idLoja,
                Long idRepositor,
                Long idProduto,
                String lote,
                String dataTroca,
                String descricao,
                String quantidade,
                String dataFabricacao,
                String dataVencimento,
                String motivoTroca) {
}
