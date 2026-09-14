package br.com.fiap.transcricao.repository;

import br.com.fiap.transcricao.connection.ConexaoFactory;
import br.com.fiap.transcricao.model.Reuniao;

import java.util.ArrayList;
import java.util.List;

/**
 * Camada de acesso a dados (Repository/DAO) da Reuniao.
 * Simula uma tabela do banco usando uma lista em memória e passa
 * sempre pela ConexaoFactory antes de mexer nos dados.
 */
public class ReuniaoRepository {

    private final List<Reuniao> reunioes = new ArrayList<>();
    private long proximoId = 1L;

    public Reuniao inserir(Reuniao reuniao) {
        ConexaoFactory.conectar();
        reuniao.setId(proximoId++);
        reunioes.add(reuniao);
        return reuniao;
    }

    public Reuniao buscarPorId(Long id) {
        ConexaoFactory.conectar();
        for (Reuniao reuniao : reunioes) {
            if (reuniao.getId().equals(id)) {
                return reuniao;
            }
        }
        return null;
    }

    public List<Reuniao> listarTodas() {
        ConexaoFactory.conectar();
        return new ArrayList<>(reunioes);
    }

    public boolean atualizar(Reuniao reuniaoAtualizada) {
        ConexaoFactory.conectar();
        Reuniao existente = buscarPorId(reuniaoAtualizada.getId());

        if (existente == null) {
            return false;
        }

        existente.setTitulo(reuniaoAtualizada.getTitulo());
        existente.setData(reuniaoAtualizada.getData());
        existente.setPossivelSegmento(reuniaoAtualizada.getPossivelSegmento());
        existente.setAssuntos(reuniaoAtualizada.getAssuntos());
        return true;
    }

    public boolean deletar(Long id) {
        ConexaoFactory.conectar();
        Reuniao existente = buscarPorId(id);

        if (existente == null) {
            return false;
        }

        return reunioes.remove(existente);
    }
}
