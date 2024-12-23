package src.booking.Repository;

import src.booking.Models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private static ClienteRepository instance;
    private List<Cliente> clientes;

    private ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public static synchronized ClienteRepository getInstance() {
        if (instance == null) {
            instance = new ClienteRepository();
        }
        return instance;
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente findClienteByEmail(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return cliente;
            }
        }
        return null;
    }
}