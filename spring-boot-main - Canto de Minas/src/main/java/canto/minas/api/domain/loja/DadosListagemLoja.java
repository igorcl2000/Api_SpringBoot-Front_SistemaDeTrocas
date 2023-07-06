package canto.minas.api.domain.loja;

//import med.voll.api.domain.endereco.DadosEndereco;
import canto.minas.api.domain.endereco.Endereco;

public record DadosListagemLoja(Long id, String rasaoSocial, String nomeFantasia, String cnpj, String telefone,
        String responsavel, Endereco endereco) {

    public DadosListagemLoja(Loja loja) {
        this(loja.getId(), loja.getRasaoSocial(), loja.getNomeFantasia(), loja.getCnpj(), loja.getTelefone(),
                loja.getResponsavel(), loja.getEndereco());
    }

}
