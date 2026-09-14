package br.com.fiap.transcricao.teste;

import br.com.fiap.transcricao.model.AnaliseNLP;
import br.com.fiap.transcricao.model.Cliente;
import br.com.fiap.transcricao.model.Reuniao;
import br.com.fiap.transcricao.model.Transcricao;

import java.time.LocalDate;


public class TesteAnaliseNLP {

    private static int totalTestes = 0;
    private static int testesOk = 0;

    public static void main(String[] args) {

        testarAnaliseDeProdutos();
        testarAnaliseDeSentimentos();
        testarAnaliseDeAssuntos();
        testarDeteccaoDeSegmento();

        System.out.println("\n===== RESULTADO DOS TESTES =====");
        System.out.println(testesOk + " de " + totalTestes + " testes passaram.");
    }

    private static void testarAnaliseDeProdutos() {
        AnaliseNLP analise = new AnaliseNLP();

        String texto = "Hoje conversamos sobre o TOTVS Protheus e também sobre o Fluig.";
        analise.executarAnaliseReuniao(texto);

        verificar(
                "executarAnaliseReuniao encontra os produtos citados no texto",
                analise.getProdutosEncontrados().contains("TOTVS Protheus")
                        && analise.getProdutosEncontrados().contains("Fluig")
        );
    }

    private static void testarAnaliseDeSentimentos() {
        AnaliseNLP analise = new AnaliseNLP();

        String texto = "O sistema não funciona direito desde a última atualização.";
        analise.executarAnaliseReuniaoSentimentos(texto);

        verificar(
                "executarAnaliseReuniaoSentimentos encontra o sentimento negativo",
                analise.getSentimentosEncontrados().contains("não funciona")
        );
    }

    private static void testarAnaliseDeAssuntos() {
        AnaliseNLP analise = new AnaliseNLP();

        String texto = "Precisamos verificar o servidor e a infraestrutura da nuvem.";
        analise.executarAnaliseAssuntos(texto);

        verificar(
                "executarAnaliseAssuntos classifica o assunto como TI",
                analise.getAssuntosEncontrados().contains("TI")
        );
    }

    private static void testarDeteccaoDeSegmento() {
        AnaliseNLP analise = new AnaliseNLP();

        Cliente cliente = new Cliente(1L, "Empresa Teste", "00.000.000/0001-00", "Fulano");
        Transcricao transcricao = new Transcricao(1L, "Precisamos falar sobre a folha de pagamento e os benefícios.");
        Reuniao reuniao = new Reuniao(1L, "Reunião de teste", LocalDate.now(), cliente, transcricao);

        analise.executarAnaliseAssuntos(transcricao.getTextoOriginal());
        analise.adicionarAssunto(reuniao);
        analise.detectarSegmento(reuniao);

        verificar(
                "detectarSegmento identifica 'Recursos Humanos' a partir do assunto RH",
                "Recursos Humanos".equals(reuniao.getPossivelSegmento())
        );

        verificar(
                "adicionarAssunto realmente inclui o assunto na lista da reunião",
                reuniao.getAssuntos().contains("RH")
        );
    }

    private static void verificar(String descricaoDoTeste, boolean condicaoEsperada) {
        totalTestes++;
        if (condicaoEsperada) {
            testesOk++;
            System.out.println("[OK] " + descricaoDoTeste);
        } else {
            System.out.println("[FALHOU] " + descricaoDoTeste);
        }
    }
}
