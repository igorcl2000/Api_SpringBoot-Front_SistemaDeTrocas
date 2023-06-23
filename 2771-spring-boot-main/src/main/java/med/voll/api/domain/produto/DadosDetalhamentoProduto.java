package med.voll.api.domain.produto;

//import med.voll.api.domain.endereco.DadosEndereco;
//import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoProduto(Long id, String nome, String descricao, String foto, String codProd) {

    public DadosDetalhamentoProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getFoto(),
                produto.getCodProd());
    }
}
