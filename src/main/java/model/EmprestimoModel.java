package model;

import dto.FilmeVideoDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "emprestimo")
public class EmprestimoModel implements IModel {

    public static final int DAYSEMPRESTIMO = 3;
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;

    private LocalDateTime dataLocacao;
    private LocalDateTime dataDevolucao;
    private BigDecimal valorAluguel = BigDecimal.ZERO;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private ClienteModel cliente;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL)
    private List<VideoModel> videos = new ArrayList<>();

    public EmprestimoModel() {

    }

    @PrePersist
    private void PrePersist() {

        for (VideoModel video: videos) {
            this.valorAluguel = valorAluguel.add(
                    new BigDecimal(video.getValorDiario().doubleValue() * DAYSEMPRESTIMO));
        }

        if (this.valorAluguel == null) {
            this.valorAluguel = BigDecimal.ZERO;
        }

        this.dataLocacao = LocalDateTime.now();
        this.dataDevolucao = dataLocacao.plusDays(DAYSEMPRESTIMO);
        this.status = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int idEmprestimo) {
        this.id = idEmprestimo;
    }

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;

        for (VideoModel videoModel :this.videos) {
            videoModel.setStatus(false);
        }
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public List<VideoModel> getVideos() {
        return videos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmprestimoModel that = (EmprestimoModel) o;
        return id == that.id &&
                Objects.equals(dataLocacao, that.dataLocacao) &&
                Objects.equals(dataDevolucao, that.dataDevolucao) &&
                Objects.equals(valorAluguel, that.valorAluguel) &&
                Objects.equals(status, that.status) &&
                Objects.equals(cliente, that.cliente) &&
                Objects.equals(videos, that.videos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataLocacao, dataDevolucao, valorAluguel, status, cliente, videos);
    }

    @Override
    public String toString() {
        return "EmprestimoModel{" +
                "id=" + id +
                ", dataLocacao=" + dataLocacao +
                ", dataDevolucao=" + dataDevolucao +
                ", valorAluguel=" + valorAluguel +
                ", status='" + status + '\'' +
                //", cliente=" + cliente +
                //", videos=" + videos +
                '}';
    }

    @Override
    public IModel montarObjeto(ResultSet obj) throws SQLException {
        return null; // nao implementado
    }

    public void addFilme(FilmeVideoDTO filmeVideoDTO) {

        VideoModel video = new VideoModel();

        video.setValorDiario(filmeVideoDTO.valorDiario);
        video.setFilme(filmeVideoDTO.filme);
        video.setTipo(filmeVideoDTO.tipo);

        video.setEmprestimo(this);

        this.videos.add(video);
    }
}
