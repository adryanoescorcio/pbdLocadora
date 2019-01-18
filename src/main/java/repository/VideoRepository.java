package repository;

import model.IModel;
import model.VideoModel;

import javax.persistence.EntityManager;
import java.util.List;

public class VideoRepository extends Repository {

    public static final String ENTITYNAME = "VideoModel";
    public final EntityManager em;

    public VideoRepository(EntityManager em) {
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

    protected List<VideoModel> obterTodosRegistros() {
        return this.em.createQuery("from " + ENTITYNAME, VideoModel.class)
                .getResultList();
    }

    @Override
    protected VideoModel consultarEntidade(int id) {
        VideoModel objeto = em.find(VideoModel.class, id);
        if (objeto != null) {
            System.out.println("Find: " + objeto.toString());
        } else {
            System.out.println("Not find: " + id);
        }

        return objeto;
    }
}
