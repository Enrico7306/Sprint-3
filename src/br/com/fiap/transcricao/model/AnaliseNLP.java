    package br.com.fiap.transcricao.model;

    import java.util.*;

    public class AnaliseNLP extends ProcessadorTexto {
        private List<String> produtosEncontrados = new ArrayList<>();
        private List<String> sentimentosEncontrados = new ArrayList<>();
        Map<String, List<String>> assuntosRelevantes = new HashMap<>();
        private List<String> assuntosEncontrados = new ArrayList<>();

        String[] produtosChaves = {
                "TOTVS Protheus",
                "TOTVS RM",
                "Fluig",
                "Clock In",
                "Meu RH"
        };
        String[] sentimentosReuniao = {
                "deu tudo certo",
                "não funciona",
                "funcionou",
                "precisa melhorar"
        };

        public AnaliseNLP() {
            assuntosRelevantes.put(
                    "RH",
                    List.of(
                            "folha de pagamento",
                            "meu rh",
                            "funcionário",
                            "benefícios"
                    )
            );

            assuntosRelevantes.put(
                    "ADM",
                    List.of(
                            "financeiro",
                            "nota fiscal",
                            "estoque"
                    )
            );

            assuntosRelevantes.put(
                    "TI",
                    List.of(
                            "servidor",
                            "sistema",
                            "infraestrutura",
                            "cloud"
                    )
            );
        }

        public String[] getProdutosChaves() {
            return produtosChaves;
        }
@Override
        public String processarTexto(String textoLimpo){
            executarAnaliseReuniao(textoLimpo);
            executarAnaliseAssuntos(textoLimpo);
            executarAnaliseReuniaoSentimentos(textoLimpo);
            return textoLimpo;
        }
        public void executarAnaliseReuniao(String textoLimpo) {
            for (String produtos : produtosChaves) {
                if (textoLimpo.toLowerCase()
                        .contains(produtos.toLowerCase())
                        && !produtosEncontrados.contains(produtos)) {
                    produtosEncontrados.add(produtos);
                }
            }

        }

        public void executarAnaliseReuniaoSentimentos(String textoLimpo) {
            for (String sentimentos : sentimentosReuniao) {
                if (textoLimpo.toLowerCase()
                        .contains(sentimentos.toLowerCase())
                        && !sentimentosEncontrados.contains(sentimentos)) {
                    sentimentosEncontrados.add(sentimentos);
                }
            }

        }

        public void executarAnaliseAssuntos(String textoLimpo) {

            for (String categoria : assuntosRelevantes.keySet()) {

                List<String> palavras = assuntosRelevantes.get(categoria);

                for (String palavra : palavras) {

                    if (textoLimpo.toLowerCase()
                            .contains(palavra.toLowerCase())
                            && !assuntosEncontrados.contains(categoria)) {

                        assuntosEncontrados.add(categoria);
                        break;
                    }
                }
            }
        }
        public void detectarSegmento(Reuniao reuniao) {

            if (assuntosEncontrados.contains("TI")) {
                reuniao.setPossivelSegmento("Tecnologia");
            }
            else if (assuntosEncontrados.contains("RH")) {
                reuniao.setPossivelSegmento("Recursos Humanos");
            }
            else if (assuntosEncontrados.contains("ADM")) {
                reuniao.setPossivelSegmento("Administrativo");
            }
            else {
                reuniao.setPossivelSegmento("Segmento não encontrado");
            }
        }
        public Boolean adicionarAssunto(Reuniao reuniao){
            Boolean adicionou = false;
            for(String assuntos : assuntosEncontrados){
                if(!reuniao.getAssuntos().contains(assuntos)){
                    reuniao.adicionarAssuntos(assuntos);
                    adicionou = true;
                }
            } return adicionou;
        }
        public String gerarResumo(){
            return "Produtos encontrados: " + produtosEncontrados +
                    "\nSentimentos encontrados: " + sentimentosEncontrados +
                    "\nAssuntos encontrados: " + assuntosEncontrados;
        }


        public List<String> getProdutosEncontrados() {
            return produtosEncontrados;
        }

        public List<String> getSentimentosEncontrados() {
            return sentimentosEncontrados;
        }

        public Map<String, List<String>> getAssuntosRelevantes() {
            return assuntosRelevantes;
        }
        public List<String> getAssuntosEncontrados() {
            return assuntosEncontrados;
        }
    }
