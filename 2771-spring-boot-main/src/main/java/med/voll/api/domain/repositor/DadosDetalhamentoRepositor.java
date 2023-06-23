package med.voll.api.domain.repositor;

//import med.voll.api.domain.endereco.DadosEndereco;
//import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoRepositor(Long id, String nome, String cpf, String email, String telefone) {

    public DadosDetalhamentoRepositor(Repositor repositor) {
        this(repositor.getId(), repositor.getNome(), repositor.getCpf(), repositor.getEmail(),
                repositor.getTelefone());
    }
}
