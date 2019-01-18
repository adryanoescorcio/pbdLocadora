package repository;

import conn.DBConexao;
import dto.FilmeVideoDTO;
import model.ClienteModel;
import model.EmprestimoModel;
import model.FilmeModel;
import modelBuilder.ClienteBuilder;
import modelBuilder.EmprestimoModelBuilder;
import modelBuilder.FilmeBuilder;
import org.junit.*;
import service.EmprestimoService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class EmprestimoRepositoryTest {

    static EntityManager em;
    static Repository repositorio;
    private final EmprestimoService objetoServico;

    public EmprestimoRepositoryTest() {
        this.em = new DBConexao().getEntityManager();
        this.repositorio = new EmprestimoRepository(em);
        this.objetoServico = new EmprestimoService(this.em);
    }

    @Before
    public void setUp() throws Exception {
        this.repositorio.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        this.repositorio.doCommits();
    }

    @AfterClass
    public static void afterClass() throws Exception{
        em.close();
    }

    @Test
    public void gravarEntidade() {
        EmprestimoModel emprestimo = this.sampleEmprestimo();

        try {
            Assert.assertTrue(objetoServico.realizarEmprestimo(emprestimo));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(emprestimo);
        System.out.println(emprestimo.getVideos().get(0));
    }

    @Test
    public void devolverEmprestimo() {

        EmprestimoModel emprestimo = this.sampleEmprestimo();
        try {
            Assert.assertTrue(objetoServico.realizarEmprestimo(emprestimo));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        EmprestimoModel emprestimoEncontrado = this.objetoServico.consultarEntidade(emprestimo.getId());

        Assert.assertTrue(this.objetoServico.devolverEmprestimo(emprestimoEncontrado));
    }

    @Test
    public void obterTodosRegistros() {

    }

    @Test
    public void consultarEntidade() {
    }

    @Test
    public void devolucao() {
    }

    public EmprestimoModel sampleEmprestimo() {
        EmprestimoModel emprestimo = EmprestimoModelBuilder.createEmprestimoModelSample();

        ClienteModel cliente = ClienteBuilder.createClienteSample();
        ClienteRepository clienteRepository = new ClienteRepository(this.em);

        clienteRepository.gravarEntidade(cliente);

        System.out.println(cliente);

        emprestimo.setCliente(cliente);

        FilmeModel filme = FilmeBuilder.createFilmeSample();
        FilmeRepository filmeRepository = new FilmeRepository(this.em);

        filmeRepository.gravarEntidade(filme);

        System.out.println(filme);

        FilmeVideoDTO filmeVideoDTO = new FilmeVideoDTO();
        filmeVideoDTO.filme = filme;
        filmeVideoDTO.tipo = "VHS";
        filmeVideoDTO.valorDiario = BigDecimal.valueOf(3);

        emprestimo.addFilme(filmeVideoDTO);

        return emprestimo;
    }
}