package med.voll.api.domain.loja;

//import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoLoja(Long id, String rasaoSocial, String nomeFantasia, String cnpj, String telefone,
        String responsavel, Endereco endereco) {

    public DadosDetalhamentoLoja(Loja loja) {
        this(loja.getId(), loja.getRasaoSocial(), loja.getNomeFantasia(), loja.getCnpj(), loja.getTelefone(),
                loja.getResponsavel(), loja.getEndereco());
    }
}
