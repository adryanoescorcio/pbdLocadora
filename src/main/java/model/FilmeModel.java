package model;

import enums.GeneroEnum;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Entity
@Table(name = "filme")
public class FilmeModel implements IModel{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    private String titulo;
    private int anoLancamento;
    private int duracao;

    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anoLancamento=" + anoLancamento +
                ", duracao=" + duracao +
                ", genero='" + genero + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmeModel)) return false;
        FilmeModel filme = (FilmeModel) o;
        return id == filme.id &&
                anoLancamento == filme.anoLancamento &&
                duracao == filme.duracao &&
                Objects.equals(titulo, filme.titulo) &&
                Objects.equals(genero, filme.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, anoLancamento, duracao, genero);
    }

    @Override
    public IModel montarObjeto(ResultSet rs) throws SQLException {

        this.setId(rs.getInt("id"));
        this.setTitulo(rs.getString("titulo"));
        this.setAnoLancamento(rs.getInt("anoLancamento"));
        this.setDuracao(rs.getInt("duracao"));
        this.setGenero((GeneroEnum) rs.getObject("genero"));

        return this;
    }

}
