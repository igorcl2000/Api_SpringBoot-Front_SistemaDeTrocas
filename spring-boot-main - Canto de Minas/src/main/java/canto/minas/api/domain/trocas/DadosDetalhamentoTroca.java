package canto.minas.api.domain.trocas;

//import med.voll.api.domain.endereco.DadosEndereco;
//import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoTroca(Long id, Long idLoja, Long idRepositor, Long idProduto, String lote,
        String dataTroca,
        String descricao, String quantidade,
        String dataFabricacao, String dataVencimento, String motivoTroca) {

    public DadosDetalhamentoTroca(Troca troca) {
        this(troca.getId(), troca.getIdLoja(), troca.getIdRepositor(), troca.getIdProduto(),
                troca.getLote(), troca.getDataTroca(), troca.getDescricao(), troca.getQuantidade(),
                troca.getDataFabricacao(), troca.getDataVencimento(), troca.getMotivoTroca());
    }
}
