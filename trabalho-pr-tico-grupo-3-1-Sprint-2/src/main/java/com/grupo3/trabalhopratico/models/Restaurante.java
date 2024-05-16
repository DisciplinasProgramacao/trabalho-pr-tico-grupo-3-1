package com.grupo3.trabalhopratico.models;
import java.util.Scanner;

public class Restaurante {
    
    
    
    public static int Menu(){
        int opcao;
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Fazer reserva");
            System.out.println("2. Exibir mesas e fila de espera");
            System.out.println("0. Concluir Reserva");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
    }
}