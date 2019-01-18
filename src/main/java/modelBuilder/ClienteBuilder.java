package modelBuilder;

import model.ClienteModel;

import java.util.ArrayList;
import java.util.List;

public class ClienteBuilder {

    public static ClienteModel createCliente() {

        final ClienteModel cliente = new ClienteModel();

        return cliente;
    }

    public static ClienteModel createClienteSample() {

        final ClienteModel cliente = getClienteDefault();
        return cliente;
    }

    public static List<ClienteModel> createPoolClienteSample() {
        List<ClienteModel> listaClientes = new ArrayList<>();

        listaClientes.add(createClienteSample("João da Silva"));
        listaClientes.add(createClienteSample("Carlinhos de Jesus"));
        listaClientes.add(createClienteSample("Xuxa Meneghel"));
        listaClientes.add(createClienteSample("Carlos Drummond de Andrade"));

        return listaClientes;
    }

    public static ClienteModel createClienteSample(String nome) {

        ClienteModel cliente = getClienteDefault();
        cliente.setNome(nome);
        return cliente;
    }

    private static ClienteModel getClienteDefault() {
        final ClienteModel cliente = new ClienteModel();

        cliente.setNome("Adryano");
        cliente.setCpf("04953597346");
        cliente.setEndereco("Rua 25");
        cliente.addTelefone("98987041068","98981200104");

        return cliente;
    }
}