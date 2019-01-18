package model;

import modelBuilder.EmprestimoModelBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Entity
@Table(name = "video")
public class VideoModel implements IModel {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    private String tipo;
    private BigDecimal valorDiario;
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "filmeId", nullable = false)
    private FilmeModel filme;

    @ManyToOne
    @JoinColumn(name = "emprestimoId", nullable = false )
    private EmprestimoModel emprestimo = new EmprestimoModelBuilder().createEmprestimoModel();

    public int getId() {
        return id;
    }

    public void setId(int idVideo) {
        this.id = idVideo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(BigDecimal valorDiario) {
        this.valorDiario = valorDiario;
    }

    public FilmeModel getFilme() {
        return filme;
    }

    public void setFilme(FilmeModel filme) {
        this.filme = filme;
    }

    public void setEmprestimo(EmprestimoModel emprestimo) {
        this.emprestimo = emprestimo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoModel that = (VideoModel) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(tipo, that.tipo) &&
                Objects.equals(valorDiario, that.valorDiario) &&
                Objects.equals(filme, that.filme) &&
                Objects.equals(emprestimo, that.emprestimo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, valorDiario, status, filme, emprestimo);
    }

    @Override
    public String toString() {
        return "VideoModel{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", valorDiario=" + valorDiario +
                ", status=" + status +
                ", filme=" + filme +
                ", emprestimo=" + emprestimo +
                '}';
    }

    @Override
    public IModel montarObjeto(ResultSet obj) throws SQLException {
        return null; //não implementado
    }

}
