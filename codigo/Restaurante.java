import java.util.Scanner;

public class Restaurante {
    
    
    
    static int Menu(){
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
    
    
    
    
    public static void main(String[] args) {
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