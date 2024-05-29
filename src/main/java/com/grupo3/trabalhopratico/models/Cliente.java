package com.grupo3.trabalhopratico.models;

public class Cliente {
    
        
        private int idCliente;
        private String nome;
        private static int contadorId = 1;
        
        public Cliente(String nome) {
          setNome(nome); 
          this.idCliente = contadorId++;
        }
      
        public String getNome() {
          return nome;
        }
      
        public void setNome(String nome) {
          this.nome = nome;
        }
      
        public int getIdCliente() {
          return idCliente;
        }

        public void setIdCliente(int idCliente) {
          this.idCliente = idCliente;
        }
      
}