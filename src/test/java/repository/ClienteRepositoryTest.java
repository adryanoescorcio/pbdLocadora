package repository;

import conn.DBConexao;
import model.ClienteModel;
import modelBuilder.ClienteBuilder;
import org.junit.*;
import service.ClienteService;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepositoryTest {

    static EntityManager em;
    static Repository repositorio;
    private final ClienteService clienteService;

    public ClienteRepositoryTest() {
        this.em = new DBConexao().getEntityManager();
        this.repositorio = new EmprestimoRepository(em);
        this.clienteService = new ClienteService(this.em);
    }

    @Before
    public void setUp() throws Exception {
        this.repositorio.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        this.repositorio.doRollback();
    }

    @AfterClass
    public static void afterClass() throws Exception{
        em.close();
    }

    @Test
    public void gravarEntidade() {
        ClienteModel cliente = ClienteBuilder.createClienteSample();
        Assert.assertTrue(this.clienteService.gravarEntidade(cliente));
    }

    @Test
    public void AtualizarEntidade() throws CloneNotSupportedException {
        ClienteModel clienteAntigo = ClienteBuilder.createClienteSample();

        this.clienteService.gravarEntidade(clienteAntigo);

        ClienteModel clienteNovo = ClienteBuilder.createClienteSample();
        clienteNovo.setId(clienteAntigo.getId());
        clienteNovo.setNome("Maria da Silva");

        Assert.assertTrue(this.clienteService.gravarEntidade(clienteNovo));
    }

    @org.junit.Test
    public void removerEntidade() {
        ClienteModel clienteAntigo = ClienteBuilder.createClienteSample();
        this.clienteService.gravarEntidade(clienteAntigo);

        try {
            Assert.assertTrue(this.clienteService.removerEntidadeClienteModel(clienteAntigo));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @org.junit.Test
    public void listarEntidade() {
        List<ClienteModel> poolClienteSample = ClienteBuilder.createPoolClienteSample();

        for (ClienteModel cliente : poolClienteSample) {
            this.clienteService.gravarEntidade(cliente);
        }

        em.flush();
        em.clear();

        List<ClienteModel> poolClienteBanco = this.clienteService.obterTodosRegistros();
        System.out.println("-------");
        poolClienteBanco.forEach(cliente -> System.out.println(cliente));

        Assert.assertTrue(poolClienteBanco.toArray().length >= 4);

        System.out.println();
    }

    @org.junit.Test
    public void consultarEntidadePorId() {
        ClienteModel clienteAntigo = ClienteBuilder.createClienteSample();
        this.clienteService.gravarEntidade(clienteAntigo);

        ClienteModel clienteEncontrado = this.clienteService.consultarEntidade(clienteAntigo.getId());
        ClienteModel clienteFalso = ClienteBuilder.createClienteSample();
        clienteFalso.setNome("Jorge Luis");

        Assert.assertEquals(clienteAntigo,clienteEncontrado);
        Assert.assertNotEquals(clienteAntigo,clienteFalso);
    }

}