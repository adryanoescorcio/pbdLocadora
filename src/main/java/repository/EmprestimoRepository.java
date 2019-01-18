package repository;

import model.EmprestimoModel;
import model.IModel;

import javax.persistence.EntityManager;
import java.util.List;

public class EmprestimoRepository extends Repository {

    public static final String ENTITYNAME = "EmprestimoModel";
    public final EntityManager em;

    public EmprestimoRepository(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    @Deprecated
    protected boolean gravarEntidade(IModel objetoNovo) {
        return false;
    }

    public boolean realizarEmprestimo(EmprestimoModel emprestimoModel){
       return super.gravarEntidade(emprestimoModel);
    }

    @Override
    @Deprecated
    protected boolean removerEntidade(IModel entidadeRemover) {
        return false;
    }

    public boolean devolverEmprestimo(EmprestimoModel entidade) {
        entidade.setStatus(false);
        super.gravarEntidade(entidade);
        System.out.println("Emprestimo devolvido com sucesso.");
        return true;
    }

    public List<EmprestimoModel> obterTodosRegistros() {
        return this.em.createQuery("from " + ENTITYNAME, EmprestimoModel.class)
                .getResultList();
    }

    @Override
    public EmprestimoModel consultarEntidade(int id) {
        EmprestimoModel objeto = em.find(EmprestimoModel.class, id);
        if (objeto != null) {
            System.out.println("Find: " + objeto.toString());
        } else {
            System.out.println("Not find: " + id);
        }

        return objeto;
    }

//    public String Efetuar(EmprestimoModel _emprestimo, List<VideoModel> listaDeVideos) throws SQLException {
//
//        VideoRepository daoVideo = new VideoRepository(conn);
//
//        _emprestimo.setIdEmprestimo(
//                this.inserir(_emprestimo));
//
//        for (VideoModel video : listaDeVideos) {
//            video.setObjEmprestimo(_emprestimo);
//            video.setIdVideo(
//                    daoVideo.inserir(video));
//
//            _emprestimo.getVideos().add(video);
//        }
//
//        return " Operação realizada com sucesso! Veja os Detalhes: \n ---- \n " +
//                _emprestimo.toString() +
//                " \n ---- \n " ;
//    }

}
