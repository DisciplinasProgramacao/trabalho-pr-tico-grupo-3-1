package com.grupo3.trabalhopratico.models;

import java.time.LocalDateTime;




public class Requisicao {
    
    
    private int id;
    private String tipo;
    private Mesa mesa;
    private Cliente cliente;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private boolean status;
    private int quant; 
    private static int identificacao ;



    private void init(Cliente cliente, int quant, boolean status){
        this.cliente = cliente;
        this.quant = quant;
        this.status = status;
        this.entrada = LocalDateTime.now();
        this.id=identificacao;
        identificacao++;
    }

    
    
    public void FecharConta(){
        saida = LocalDateTime.now();
        mesa.setOcupada(false);
        this.status = false;
    }    

    public Requisicao (Cliente cliente, int qPessoas, boolean status){
        init(cliente, qPessoas, status);
    }



    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public int getId(int id) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
        
    }



	



    

