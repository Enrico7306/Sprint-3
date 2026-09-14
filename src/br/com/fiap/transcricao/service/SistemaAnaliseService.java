package br.com.fiap.transcricao.service;

import br.com.fiap.transcricao.model.Cliente;
import br.com.fiap.transcricao.model.Reuniao;
import br.com.fiap.transcricao.repository.ClienteRepository;
import br.com.fiap.transcricao.repository.ReuniaoRepository;

import java.util.List;

/**
 * Camada de serviço: concentra as regras de uso do sistema e repassa a
 * persistência para os repositories (banco de dados).
 */
public class SistemaAnaliseService {

    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final ReuniaoRepository reuniaoRepository = new ReuniaoRepository();

    public Cliente adicionarCliente(Cliente cliente) {
        return clienteRepository.inserir(cliente);
    }

    public Reuniao adicionarReuniao(Reuniao reuniao) {
        return reuniaoRepository.inserir(reuniao);
    }

    public List<Cliente> getClientes() {
        return clienteRepository.listarTodos();
    }

    public Cliente buscarClienteporId(Long id) {
        return clienteRepository.buscarPorId(id);
    }

    public List<Reuniao> getReuniao() {
        return reuniaoRepository.listarTodas();
    }

    public Reuniao buscarReuniaoporId(Long id) {
        return reuniaoRepository.buscarPorId(id);
    }

    public boolean atualizarReuniao(Reuniao reuniao) {
        return reuniaoRepository.atualizar(reuniao);
    }

    public boolean removerReuniao(Long id) {
        return reuniaoRepository.deletar(id);
    }

    public boolean removerCliente(Long id) {
        return clienteRepository.deletar(id);
    }
}
