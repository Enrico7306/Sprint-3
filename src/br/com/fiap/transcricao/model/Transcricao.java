package br.com.fiap.transcricao.model;

public class Transcricao {
    private Long id;
    private String textoOriginal;
    private String textoLimpo;
    public Transcricao(Long id,
                       String textoOriginal) {

        this.id = id;
        this.textoOriginal = textoOriginal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public String getTextoLimpo() {
        return textoLimpo;
    }

    public void setTextoLimpo(String textoLimpo) {
        this.textoLimpo = textoLimpo;
    }
}
