package repository;

import conn.DBConexao;
import model.FilmeModel;
import modelBuilder.FilmeBuilder;
import org.junit.*;

import javax.persistence.EntityManager;
import java.util.List;

public class FilmeRepositoryTest {

    static EntityManager em;
    private final FilmeRepository ObjetoRepository;

    public FilmeRepositoryTest() {
        this.em = new DBConexao().getEntityManager();
        this.ObjetoRepository = new FilmeRepository(this.em);
    }

    @Before
    public void setUp() throws Exception {
        this.ObjetoRepository.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        this.ObjetoRepository.doRollback();
    }

    @AfterClass
    public static void afterClass() throws Exception{
        em.close();
    }

    @Test
    public void gravarEntidade() {
        FilmeModel obj = FilmeBuilder.createFilmeSample();
        Assert.assertTrue(this.ObjetoRepository.gravarEntidade(obj));
    }

    @Test
    public void AtualizarEntidade() throws CloneNotSupportedException {
        FilmeModel objAntigo = FilmeBuilder.createFilmeSample();

        this.ObjetoRepository.gravarEntidade(objAntigo);

        FilmeModel objNovo = FilmeBuilder.createFilmeSample();
        objNovo.setId(objAntigo.getId());
        objNovo.setTitulo("Star Wars II");

        Assert.assertTrue(this.ObjetoRepository.gravarEntidade(objNovo));
    }

    @org.junit.Test
    public void removerEntidade() {
        FilmeModel objAntigo = FilmeBuilder.createFilmeSample();
        this.ObjetoRepository.gravarEntidade(objAntigo);

        Assert.assertTrue(this.ObjetoRepository.removerEntidade(objAntigo.getId()));
    }

    @org.junit.Test
    public void listarEntidade() {
        List<FilmeModel> poolObjSample = FilmeBuilder.createPoolFilmeSample();

        for (FilmeModel obj : poolObjSample) {
            this.ObjetoRepository.gravarEntidade(obj);
        }

        em.flush();
        em.clear();

        List<FilmeModel> poolObjBanco = this.ObjetoRepository.obterTodosRegistros();
        System.out.println("-------");
        poolObjBanco.forEach(obj -> System.out.println(obj));

        Assert.assertTrue(poolObjBanco.toArray().length >= 4);

    }

    @org.junit.Test
    public void consultarEntidadePorId() {
        FilmeModel objAntigo = FilmeBuilder.createFilmeSample();
        this.ObjetoRepository.gravarEntidade(objAntigo);

        FilmeModel objEncontrado = this.ObjetoRepository.consultarEntidade(objAntigo.getId());
        FilmeModel objFalso = FilmeBuilder.createFilmeSample();
        objFalso.setTitulo("Avatar");

        Assert.assertEquals(objAntigo,objEncontrado);
        Assert.assertNotEquals(objAntigo,objFalso);
    }

}
