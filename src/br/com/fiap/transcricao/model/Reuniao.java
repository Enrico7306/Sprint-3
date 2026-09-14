package br.com.fiap.transcricao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reuniao {
    private Long id;
    private String titulo;
    private LocalDate data;
    private List<String> assuntos = new ArrayList<>();
    private Transcricao transcricao;
    private Cliente cliente;
    private String possivelSegmento;
    public Reuniao(Long id,String titulo,LocalDate data,Cliente cliente,Transcricao transcricao) {

        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.cliente = cliente;
        this.transcricao = transcricao;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setAssuntos(List<String> assuntos) {
        this.assuntos = assuntos;
    }
    public void adicionarAssuntos(String assunto){
        assuntos.add(assunto);
    }

    public List<String> getAssuntos() {
        return assuntos;
    }
    public void adicionarSegmento(String possivelSegmento){
        this.possivelSegmento= possivelSegmento;
    }

    public void setPossivelSegmento(String possivelSegmento) {
        this.possivelSegmento = possivelSegmento;
    }

    public Transcricao getTranscricao() {
        return transcricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPossivelSegmento() {
        return possivelSegmento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
