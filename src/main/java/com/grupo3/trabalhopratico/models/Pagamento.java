package com.grupo3.trabalhopratico.models;

import jakarta.persistence.*;

@Entity
@Table
public class Pagamento {
    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    public Long id;
    private double valorBrutoTotal;
    private double valorLiquidoTotal;
    private double valorPago;
    private double valorDescontado;
    public String metodoPagamento;
    public int dataPagamento;

    public Pagamento() {

    }

    public Pagamento(Long id, double valorBrutoTotal, double valorLiquidoTotal, double valorPago, double valorDescontado, String metodoPagamento, int dataPagamento) {
        this.id = id;
        this.valorBrutoTotal = valorBrutoTotal;
        this.valorLiquidoTotal = valorLiquidoTotal;
        this.valorPago = valorPago;
        this.valorDescontado = valorDescontado;
        this.metodoPagamento = metodoPagamento;
        this.dataPagamento = dataPagamento;
    }

    public Pagamento(double valorBrutoTotal, double valorLiquidoTotal, double valorPago, double valorDescontado) {
        this.valorBrutoTotal = valorBrutoTotal;
        this.valorLiquidoTotal = valorLiquidoTotal;
        this.valorPago = valorPago;
        this.valorDescontado = valorDescontado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorBrutoTotal() {
        return valorBrutoTotal;
    }

    public void setValorBrutoTotal(double valorBrutoTotal) {
        this.valorBrutoTotal = valorBrutoTotal;
    }

    public double getValorLiquidoTotal() {
        return valorLiquidoTotal;
    }

    public void setValorLiquidoTotal(double valorLiquidoTotal) {
        this.valorLiquidoTotal = valorLiquidoTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorDescontado() {
        return valorDescontado;
    }

    public void setValorDescontado(double valorDescontado) {
        this.valorDescontado = valorDescontado;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public int getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(int dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", valorBrutoTotal='" + valorBrutoTotal + '\'' +
                ", valorLiquidoTotal=" + valorLiquidoTotal + '\'' +
                ", valorPago=" + valorPago + '\'' +
                ", valorDescontado=" + valorDescontado + '\'' +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", dataPagamento=" + dataPagamento + '\'' +
                '}';
    }
}
