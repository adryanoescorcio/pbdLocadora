package service;

import model.ClienteModel;
import model.EmprestimoModel;
import repository.EmprestimoRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class EmprestimoService {

    private final EmprestimoRepository objetoRepository;
    private final EntityManager em;

    public EmprestimoService(EntityManager em) {
        objetoRepository = new EmprestimoRepository(em);
        this.em = em;
    }

    public boolean realizarEmprestimo(EmprestimoModel emprestimoModel) throws Exception {

        if(!this.consultaEmprestimoAtivoCliente(emprestimoModel.getCliente())){
            throw new Exception("Cliente ja possui em emprestimo ativo.");
        } else {
            System.out.println("Cliente está sem débito.");
        }

        return objetoRepository.realizarEmprestimo(emprestimoModel);
    }

    private boolean consultaEmprestimoAtivoCliente(ClienteModel cliente) {
        return new ClienteService(this.em).consultarEmprestimoAtivo(cliente);
    }

    public boolean devolverEmprestimo(EmprestimoModel entidade) {
        return objetoRepository.devolverEmprestimo(entidade);
    }

    public List<EmprestimoModel> obterTodosRegistros() {
        return objetoRepository.obterTodosRegistros();
    }

    public EmprestimoModel consultarEntidade(int id) {
        return objetoRepository.consultarEntidade(id);
    }
}
