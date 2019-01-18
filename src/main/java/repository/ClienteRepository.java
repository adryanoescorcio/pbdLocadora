package repository;

import model.ClienteModel;
import model.IModel;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepository extends Repository {

    public static final String ENTITYNAME = "ClienteModel";
    public final EntityManager em;

    public ClienteRepository(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public boolean gravarEntidade(IModel objetoNovo) {
        return super.gravarEntidade(objetoNovo);
    }

    @Override
    @Deprecated
    public boolean removerEntidade(IModel entidadeRemover) {
        return super.removerEntidade(entidadeRemover);
    }

    public List<ClienteModel> obterTodosRegistros() {
        return this.em.createQuery("from " + ENTITYNAME, ClienteModel.class)
                .getResultList();
    }

    @Override
    public ClienteModel consultarEntidade(int id) {
        ClienteModel objeto = em.find(ClienteModel.class, id);
        if (objeto != null) {
            System.out.println("Find: " + objeto.toString());
        } else {
            System.out.println("Not find: " + id);
        }

        return objeto;
    }

    public boolean consultaEmprestimoAtivoCliente(int id) {
        List<ClienteModel> clientes =  em.createQuery(
                " SELECT c FROM " + ENTITYNAME + " c INNER JOIN EmprestimoModel e ON idCliente = id " +
                        " WHERE e.status = 1 AND c.status = 1 AND id = :id  ", ClienteModel.class)
                .setParameter("id", id )
                .getResultList();

        if (clientes.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
