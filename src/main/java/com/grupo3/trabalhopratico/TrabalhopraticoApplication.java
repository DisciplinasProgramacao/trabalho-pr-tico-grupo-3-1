package com.grupo3.trabalhopratico;

import com.grupo3.trabalhopratico.produto.Produto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication

public class TrabalhopraticoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhopraticoApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        Requisicao restaurante = new Requisicao(10, 4, 4, 2);
        int opcao;
        do {
            opcao = Menu();
            switch (opcao) {
                case 1:
                    System.out.print("Quantas pessoas são? ");
                    int pessoas = scanner.nextInt();
                    restaurante.fazerReserva(pessoas);
                    break;
                case 0:
                    System.out.println("Reserva Concluida !");
                    break;
                case 2:
                    restaurante.exibirMesas();
                    break;
            }

        }while (opcao != 0);


        boolean sair = false;
        while (!sair) {



            switch (opcao) {
                case 1:


            }
        }

        scanner.close();
    }
}



