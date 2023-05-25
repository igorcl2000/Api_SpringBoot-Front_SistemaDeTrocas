package med.volli.api.funcionario;

public record DadosListagemFuncionario(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemFuncionario(Funcionario medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}