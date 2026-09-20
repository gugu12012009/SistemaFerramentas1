package com.example.sistemaferramentas;

public class Ferramenta {
    private long id;
    private String nome;
    private int quantidade;
    private String localizacao;

    public Ferramenta() {
    }

    public Ferramenta(long id, String nome, int quantidade, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.localizacao = localizacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
