import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class init {



    public static  void main(String args[]) throws SQLException {
/*
        DBConexao db = new DBConexao();
        Connection conn = db.getConexao();

        System.out.println(conn.isClosed());


        AlugarFilmeTest(conn);
*/
        EntityManagerFactory factory;
        EntityManager em;
    }

    private static void AlugarFilmeTest(Connection conn) throws SQLException {
/*
        List<Filme> listaFilmesSelecionados = new ArrayList<>();
        List<Video> listaVideosParaLocar = new ArrayList<>();

        // inserir os filmes que serão locados

        FilmeRepository daoFilme = new FilmeRepository(conn);

        List<Filme> filmesIndisponiveis = daoFilme.VerificarDisponibilidadeFilme(listaFilmesSelecionados);

        listaFilmesSelecionados.removeAll(filmesIndisponiveis);

        final double soma = listaVideosParaLocar.stream().mapToDouble(x -> x.getValorDiario().doubleValue()).sum();
        final BigDecimal somaValorDiario = new BigDecimal(soma);
*/
    }

}
