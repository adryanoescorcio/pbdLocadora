package repository;

import model.IModel;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public abstract class Repository {

    private EntityManager em;

    public Repository (final EntityManager em) {
        this.em = em;
    }

    public void beginTransaction() {
        this.em.getTransaction().begin();
    }

    public void doCommits() {
        this.em.getTransaction().commit();
        this.em.clear(); // limpa a conexao
    }

    protected boolean gravarEntidade(final IModel objetoNovo) {

        try {
            // verificar se a entidade já existe no banco de dados
            final IModel entidadeRecuperadaBD = this.consultarEntidade(objetoNovo
                    .getId());

            // se não existir no BD, persistir entidade
            if (entidadeRecuperadaBD == null) {
                return this.InserirEntidade(objetoNovo);
            } else {
                // se existir no BD, verificar se a entidade foi alterada.
                if (entidadeRecuperadaBD.toString().equals(objetoNovo.toString())) {
                    System.out.println("Sem alteração");
                    return true;
                } else {
                    // a entidade foi alterada, então atualize BD
                    return this.atualizarEntidade(objetoNovo);
                }
            }

        } catch (NullPointerException | SQLException e) {
            System.out.println("Erro ao persistir objeto");
            this.doRollback();
            return false;

        } finally {
        }
    }

    public void doRollback() {
        this.em.getTransaction().rollback();
    }

    private boolean InserirEntidade(final IModel entidade)
            throws SQLException {
        em.persist(entidade);
        System.out.println("Inserido com Sucesso");
        return true;
    }

    private boolean atualizarEntidade(final IModel entidadeAtualizar) {
        try {
            // atualizar
            em.merge(entidadeAtualizar);
            System.out.println("Atualizado com Sucesso");
            return true;

        } catch (final Exception e) {
            System.out.println("Erro ao atualizar entidade");
            return false;
        }
    }

    protected abstract IModel consultarEntidade(int key);

    protected boolean removerEntidade(final IModel entidadeRemover) {

        try {
            em.remove(entidadeRemover);
            System.out.println("Removido com sucesso");
            return true;
        } catch (final Exception e) {
            System.out.println("Erro ao remover objeto");
            this.doRollback();
            return false;
        } finally {
        }
    }

    protected boolean removerEntidade(int id) {

        try {
            IModel entidadeRemover = this.consultarEntidade(id);
            if (entidadeRemover != null) {
                em.remove(entidadeRemover);
                System.out.println("Removido com sucesso");
                return true;
            }
            return false;
        } catch (final Exception e) {
            System.out.println("Erro ao remover objeto");
            this.doRollback();
            return false;
        } finally {
        }
    }
}
