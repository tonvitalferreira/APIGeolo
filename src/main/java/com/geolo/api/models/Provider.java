package com.geolo.api.models;

public class Provider {

    private String nome, endereco;
    private LatLong latLong;

    public Provider(String nome, String endereco, LatLong latLong) {
        this.nome = nome;
        this.endereco = endereco;
        this.latLong = latLong;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LatLong getLatLong() {
        return latLong;
    }

    public void setLatLong(LatLong latLong) {
        this.latLong = latLong;
    }

}
