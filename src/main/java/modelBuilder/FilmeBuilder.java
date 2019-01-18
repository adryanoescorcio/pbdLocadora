package modelBuilder;

import enums.GeneroEnum;
import model.FilmeModel;

import java.util.ArrayList;
import java.util.List;

public class FilmeBuilder {

    public static List<FilmeModel> createPoolFilmeSample() {
        List<FilmeModel> listaFilmes = new ArrayList<>();

        listaFilmes.add(createFilmeSample("Branca de Neves"));
        listaFilmes.add(createFilmeSample("Jovens Titãs"));
        listaFilmes.add(createFilmeSample("Principe da Persia"));
        listaFilmes.add(createFilmeSample("Star Trek"));

        return listaFilmes;
    }

    public static FilmeModel createFilme() {

        final FilmeModel filme = new FilmeModel();

        return filme;
    }

    public static FilmeModel createFilmeSample() {

        final FilmeModel filme = getFilmeDefault();
        return filme;
    }

    public static FilmeModel createFilmeSample(String nome) {

        FilmeModel filme = getFilmeDefault();
        filme.setTitulo(nome);
        return filme;
    }

    private static FilmeModel getFilmeDefault() {
        final FilmeModel filme = new FilmeModel();

        filme.setTitulo("Adryano");
        filme.setDuracao(120);
        filme.setGenero(GeneroEnum.ACAO);
        filme.setAnoLancamento(2012);

        return filme;
    }
}