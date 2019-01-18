package repository;

import model.FilmeModel;
import model.IModel;

import javax.persistence.EntityManager;
import java.util.List;

public class FilmeRepository extends Repository {

    public static final String ENTITYNAME = "FilmeModel";
    public final EntityManager em;

    public FilmeRepository(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    protected boolean gravarEntidade(IModel objetoNovo) {
        return super.gravarEntidade(objetoNovo);
    }

    @Override
    protected boolean removerEntidade(IModel entidadeRemover) {
        return super.removerEntidade(entidadeRemover);
    }

    protected List<FilmeModel> obterTodosRegistros() {
        return this.em.createQuery("from " + ENTITYNAME, FilmeModel.class)
                .getResultList();
    }

    @Override
    protected FilmeModel consultarEntidade(int id) {
        FilmeModel obj = em.find(FilmeModel.class, id);
        if (obj != null) {
            System.out.println("Find: " + obj.toString());
        } else {
            System.out.println("Not find: " + id);
        }

        return obj;
    }
}
