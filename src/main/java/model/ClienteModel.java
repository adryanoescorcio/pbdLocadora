package model;

import modelBuilder.ClienteBuilder;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Entity
@Table(name = "cliente")
public class ClienteModel implements IModel{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int idCliente;
    private String nome;
    private String cpf;
    private String endereco;
    private boolean status = true;

    @ElementCollection
    @CollectionTable(
            name = "telefone",
            joinColumns = @JoinColumn(name = "idCliente", nullable = false)
    )
    @Column(name = "numero", nullable = false)
    private Set<String> telefones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<EmprestimoModel> emprestimos;

    public ClienteModel() {
        this.setEmprestimos(new ArrayList<>());
        telefones = new LinkedHashSet<>();
    }

    public static ClienteModel createClienteFactory() {
        final ClienteModel cliente = new ClienteBuilder().createCliente();
        return cliente;
    }

    @Override
    public IModel montarObjeto(ResultSet obj) throws SQLException {
        return null; // nao implementado
    }

    public int getId() {
        return idCliente;
    }

    public void setId(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<EmprestimoModel> getEmprestimos() {
        return emprestimos;
    }

    private void setEmprestimos(List<EmprestimoModel> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public void addTelefone(String... telefone) {
        for (String item: telefone) {
            this.telefones.add(item);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteModel that = (ClienteModel) o;
        return idCliente == that.idCliente &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(endereco, that.endereco) &&
                Objects.equals(telefones, that.telefones) &&
                Objects.equals(emprestimos, that.emprestimos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nome, cpf, endereco, telefones, emprestimos);
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefones=" + telefones +
                ", emprestimos=" + emprestimos +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
