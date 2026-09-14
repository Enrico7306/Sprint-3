package br.com.fiap.transcricao.model;

import java.util.List;

public class Cliente {
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String contatoPrincipal;
    private List<Reuniao> reunioes;

    public Cliente(Long id,String razaoSocial,String cnpj,String contatoPrincipal){
     this.id =id;
    this.razaoSocial =razaoSocial;
    this.cnpj =cnpj;
    this.contatoPrincipal =contatoPrincipal;
}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContatoPrincipal() {
        return contatoPrincipal;
    }

    public void setContatoPrincipal(String contatoPrincipal) {
        this.contatoPrincipal = contatoPrincipal;
    }
}
