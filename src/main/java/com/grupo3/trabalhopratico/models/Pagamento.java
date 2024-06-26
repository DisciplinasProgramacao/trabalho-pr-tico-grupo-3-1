package com.grupo3.trabalhopratico.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O valor pago é obrigatório")
    private double valorPago;

    @NotNull(message = "O valor descontado é obrigatório")
    private double valorDescontado;

    @NotNull(message = "O método de pagamento é obrigatório")
    private String metodoPagamento;

    @NotNull(message = "A data de pagamento é obrigatória")
    private LocalDate dataPagamento;

    public Pagamento(Long id, double valorPago, double valorDescontado, String metodoPagamento, LocalDate dataPagamento) {
        this.id = id;
        this.valorPago = valorPago;
        this.valorDescontado = valorDescontado;
        this.metodoPagamento = metodoPagamento;
        this.dataPagamento = dataPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
