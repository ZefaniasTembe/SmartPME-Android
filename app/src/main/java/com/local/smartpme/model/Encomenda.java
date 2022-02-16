package com.local.smartpme.model;

import java.util.Date;

public class Encomenda {

    private int id;
    private String clienteId;
    private String produtoId;
    private double quantidadeProduto;
    private Date diaEntrega;
    private Date diaMarcacao;
    private String localEntrega;
    private double precoUntario;

    public Encomenda() {
    }

    public Encomenda(String clienteId, String produtoId, double quantidadeProduto,
                     Date diaEntrega, String localEntrega, double precoUntario) {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidadeProduto = quantidadeProduto;
        this.diaEntrega = diaEntrega;
        this.localEntrega = localEntrega;
        this.precoUntario = precoUntario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(String produtoId) {
        this.produtoId = produtoId;
    }

    public double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Date getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(Date diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public double getPrecoUntario() {
        return precoUntario;
    }

    public void setPrecoUntario(double precoUntario) {
        this.precoUntario = precoUntario;
    }

    public Date getDiaMarcacao() {
        return diaMarcacao;
    }

    public void setDiaMarcacao(Date diaMarcacao) {
        this.diaMarcacao = diaMarcacao;
    }

    @Override
    public String toString() {
        return "Encomenda{" +
                "clienteId='" + clienteId + '\'' +
                ", produtoId='" + produtoId + '\'' +
                ", quantidadeProduto='" + quantidadeProduto + '\'' +
                ", diaEntrega=" + diaEntrega +
                ", localEntrega='" + localEntrega + '\'' +
                ", precoUntario=" + precoUntario +
                '}';
    }
}
