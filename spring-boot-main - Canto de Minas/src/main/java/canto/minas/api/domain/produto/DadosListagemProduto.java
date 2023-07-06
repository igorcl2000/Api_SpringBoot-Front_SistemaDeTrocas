package canto.minas.api.domain.produto;

//import med.voll.api.domain.endereco.DadosEndereco;
//import med.voll.api.domain.endereco.Endereco;

public record DadosListagemProduto(Long id, String nome, String descricao, String foto, String codProd) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getFoto(),
                produto.getCodProd());
    }

}
