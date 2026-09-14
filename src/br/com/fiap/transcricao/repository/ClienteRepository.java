package br.com.fiap.transcricao.repository;

import br.com.fiap.transcricao.connection.ConexaoFactory;
import br.com.fiap.transcricao.model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Camada de acesso a dados (Repository/DAO) do Cliente.
 * Simula uma tabela do banco usando uma lista em memória e passa
 * sempre pela ConexaoFactory antes de mexer nos dados, como aconteceria
 * com um banco de verdade.
 */
public class ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();
    private long proximoId = 1L;

    public Cliente inserir(Cliente cliente) {
        ConexaoFactory.conectar();
        cliente.setId(proximoId++);
        clientes.add(cliente);
        return cliente;
    }

    public Cliente buscarPorId(Long id) {
        ConexaoFactory.conectar();
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> listarTodos() {
        ConexaoFactory.conectar();
        return new ArrayList<>(clientes);
    }

    public boolean atualizar(Cliente clienteAtualizado) {
        ConexaoFactory.conectar();
        Cliente existente = buscarPorId(clienteAtualizado.getId());

        if (existente == null) {
            return false;
        }

        existente.setRazaoSocial(clienteAtualizado.getRazaoSocial());
        existente.setCnpj(clienteAtualizado.getCnpj());
        existente.setContatoPrincipal(clienteAtualizado.getContatoPrincipal());
        return true;
    }

    public boolean deletar(Long id) {
        ConexaoFactory.conectar();
        Cliente existente = buscarPorId(id);

        if (existente == null) {
            return false;
        }

        return clientes.remove(existente);
    }
}
