package conn;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConexao {

    private MysqlDataSource pool;
    private EntityManagerFactory factory;
    private EntityManager em;

    public DBConexao() {
        IniciarConexaoJPA();
    }

    private void IniciarPoolConexao() {

        MysqlDataSource pool;

        String url = "jdbc:mysql://localhost/dblocadora?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String pw = "";

        pool = new MysqlDataSource();
        pool.setURL(url);
        pool.setUser(user);
        pool.setPassword(pw);

        this.pool = pool;
    }

    private void IniciarConexaoJPA(){
        // Conexao com JPA
        try{
            factory = Persistence.createEntityManagerFactory("conexaoJPA"); // setando o objeto persistence.xml
            em = factory.createEntityManager(); // criando porta de conexao
            System.out.println("Conexão EntityManager: " + getEntityManager().isOpen());

        } catch(Exception e) {
            System.out.println("Erro: conexao JPA: " +e.getMessage());
        }
    }

    public Connection getConexao() {
        try(final Connection connection = pool.getConnection()){
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(" Não foi possivel conectar \n" + e.getMessage());
        }

    }

    public EntityManager getEntityManager() {
        return em;
    }
}
