package br.com.fiap.transcricao.main;

import br.com.fiap.transcricao.connection.ConexaoFactory;
import br.com.fiap.transcricao.model.*;
import br.com.fiap.transcricao.service.SistemaAnaliseService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        // Abre a conexão com o banco (usuário e senha fixos, dentro da classe).
        ConexaoFactory.conectar();

        SistemaAnaliseService sistemaAnaliseService = new SistemaAnaliseService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome da empresa:");
        String razaoSocial = scanner.nextLine();

        System.out.println("CNPJ:");
        String cnpj = scanner.nextLine();

        System.out.println("Contato principal:");
        String contato = scanner.nextLine();

        Cliente cliente = new Cliente(
                null,
                razaoSocial,
                cnpj,
                contato
        );
        cliente = sistemaAnaliseService.adicionarCliente(cliente);

        System.out.println("Título da reunião:");
        String titulo = scanner.nextLine();

        System.out.println("Digite a transcrição (digite FIM para encerrar):");

        StringBuilder texto = new StringBuilder();

        while (true) {

            String linha = scanner.nextLine();

            if (linha.equalsIgnoreCase("FIM")) {
                break;
            }

            texto.append(linha).append("\n");
        }

        String textoOriginal = texto.toString();
        Transcricao transcricao = new Transcricao(
                null,
                textoOriginal
        );

        Reuniao reuniao = new Reuniao(
                null,
                titulo,
                LocalDate.now(),
                cliente,
                transcricao
        );

        FiltrodeSeguranca filtrodeSeguranca = new FiltrodeSeguranca();

        String textoLimpo = filtrodeSeguranca.processarTexto(reuniao.getTranscricao().getTextoOriginal());
        transcricao.setTextoLimpo(textoLimpo);

        AnaliseNLP analise = new AnaliseNLP();

        analise.executarAnaliseReuniao(textoLimpo);
        analise.executarAnaliseReuniaoSentimentos(textoLimpo);
        analise.executarAnaliseAssuntos(textoLimpo);
        analise.adicionarAssunto(reuniao);
        analise.detectarSegmento(reuniao);

        // Só salvamos a reunião no banco depois de rodar a análise, para
        // já gravar segmento, assuntos e texto limpo em uma única gravação.
        reuniao = sistemaAnaliseService.adicionarReuniao(reuniao);

        System.out.println("\n===== RELATÓRIO =====");
        System.out.println("Empresa: " + cliente.getRazaoSocial());
        System.out.println("Contato: " + cliente.getContatoPrincipal());
        System.out.println("Título: " + reuniao.getTitulo());
        System.out.println("Data: " + reuniao.getData());

        System.out.println("Produtos encontrados: "
                + analise.getProdutosEncontrados());

        System.out.println("Sentimentos encontrados: "
                + analise.getSentimentosEncontrados());

        System.out.println("Assuntos encontrados: "
                + reuniao.getAssuntos());

        System.out.println("Segmento detectado: "
                + reuniao.getPossivelSegmento());

        System.out.println("Dados mascarados: "
                + filtrodeSeguranca.getDadosMascaradosContados());

        System.out.println("\nReunião salva no banco com o id: " + reuniao.getId());

        scanner.close();
    }
}
