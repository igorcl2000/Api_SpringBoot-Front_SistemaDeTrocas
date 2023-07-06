package canto.minas.api.domain.repositor;

//import med.voll.api.domain.endereco.DadosEndereco;
//import med.voll.api.domain.endereco.Endereco;

public record DadosListagemRepositor(Long id, String nome, String cpf, String email, String telefone) {

    public DadosListagemRepositor(Repositor repositor) {
        this(repositor.getId(), repositor.getNome(), repositor.getCpf(), repositor.getEmail(),
                repositor.getTelefone());
    }

}
