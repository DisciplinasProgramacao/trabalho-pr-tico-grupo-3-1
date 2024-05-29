package com.grupo3.trabalhopratico.models;


public class Mesa {
    private int id;
    private int capacidade;
    private boolean status;

    public Mesa(int id) {
        this.id = id;
        this.status = false;
    
        if(capacidade <= 0){
            this.capacidade =4;
        }else if(capacidade>4 || capacidade <=6){
            this.capacidade =6;
        }else if(capacidade>6){
            this.capacidade=8;
        }    
    }


    public int getId() {
        return id;
    }
    public int getCapacidade() {
        return capacidade;
    }

    public boolean StatusMesa() {
        return status;
    }
    
    
    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Id Mesa: " + id + ", Capacidade: " + capacidade;
    }

    public boolean setOcupada(boolean status) {
        return status = false;
    }


   
}
