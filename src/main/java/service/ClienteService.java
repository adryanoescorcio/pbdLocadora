package service;

import model.ClienteModel;
import model.IModel;
import repository.ClienteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteService {

    private final ClienteRepository objetoRepository;
    private final EntityManager em;

    public ClienteService(EntityManager em) {
        objetoRepository = new ClienteRepository(em);
        this.em = em;
    }

    public boolean consultarEmprestimoAtivo(ClienteModel cliente) {
        return objetoRepository.consultaEmprestimoAtivoCliente(cliente.getId());
    }

    public boolean gravarEntidade(IModel objetoNovo) {
        return objetoRepository.gravarEntidade(objetoNovo);
    }

    public boolean removerEntidadeClienteModel (ClienteModel entidadeRemover) throws Exception {

        if(consultaEmprestimoAtivoCliente(entidadeRemover.getId())){
            throw new Exception("Cliente ja possui em emprestimo ativo.");
        } else {
            System.out.println("Cliente está sem debito.");
        }

        entidadeRemover.setStatus(false);

        return objetoRepository.gravarEntidade(entidadeRemover);
    }

    public List<ClienteModel> obterTodosRegistros() {
        return objetoRepository.obterTodosRegistros();
    }

    public ClienteModel consultarEntidade(int id) {
        return objetoRepository.consultarEntidade(id);
    }

    public boolean consultaEmprestimoAtivoCliente(int id) {
        return objetoRepository.consultaEmprestimoAtivoCliente(id);
    }
}
