package br.com.fiap.transcricao.model;

public class FiltrodeSeguranca extends ProcessadorTexto {

    private int dadosMascaradosContados;
    private boolean statusConformidade;

    @Override
    public String processarTexto(String texto) {
        return mascararDadosSensiveis(texto);
    }

    public String mascararDadosSensiveis(String texto) {

        String textoLimpo = texto;

        if (textoLimpo.contains("@")) {

            textoLimpo = textoLimpo.replaceAll(
                    "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
                    "[EMAIL_OCULTO]"
            );

            dadosMascaradosContados++;
        }

        // Mascara CNPJ
        if (textoLimpo.matches("(?s).*\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}.*")) {

            textoLimpo = textoLimpo.replaceAll(
                    "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
                    "[CNPJ_OCULTO]"
            );

            dadosMascaradosContados++;
        }

        statusConformidade = true;

        return textoLimpo;
    }


    public int getDadosMascaradosContados() {
        return dadosMascaradosContados;
    }

    public boolean isStatusConformidade() {
        return statusConformidade;
    }
}