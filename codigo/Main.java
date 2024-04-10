import java.util.ArrayList;
import java.util.Scanner;

class Cliente {
    private String nome;
    private int idade;
    private String dataEntrada;

    public Cliente(String nome, int idade, String dataEntrada) {
        this.nome = nome;
        this.idade = idade;
        this.dataEntrada = dataEntrada;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void fazerRequisicao(Restaurante restaurante) {
        restaurante.chegadaCliente(this);
    }
}

class Mesa {
    private boolean ocupacao;
    private Cliente[] clientes;
    private int totalDeLugar;

    public Mesa(int totalDeLugar) {
        this.ocupacao = false;
        this.clientes = new Cliente[totalDeLugar];
        this.totalDeLugar = totalDeLugar;
    }

    public void inserirCliente(Cliente cliente) {
        if (!ocupacao) {
            for (int i = 0; i < totalDeLugar; i++) {
                if (clientes[i] == null) {
                    clientes[i] = cliente;
                    ocupacao = true;
                    break;
                }
            }
        }
    }

    public void removerCliente(Cliente cliente) {
        for (int i = 0; i < totalDeLugar; i++) {
            if (clientes[i] == cliente) {
                clientes[i] = null;
                break;
            }
        }
        ocupacao = false;
        for (Cliente c : clientes) {
            if (c != null) {
                ocupacao = true;
                break;
            }
        }
    }

    public boolean verificarOcupacao() {
        return ocupacao;
    }

    public int getTotalDeLugar() {
        return totalDeLugar;
    }
}

class FilaDeEspera {
    private ArrayList<Cliente> clientesNaFila;

    public FilaDeEspera() {
        clientesNaFila = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientesNaFila.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        clientesNaFila.remove(cliente);
    }

    public Cliente verificarProximo() {
        if (!clientesNaFila.isEmpty()) {
            return clientesNaFila.get(0);
        }
        return null;
    }

    public int tamanhoDaFila() {
        return clientesNaFila.size();
    }
}

class Restaurante {
    private Mesa[] totalMesas;
    private FilaDeEspera filaDeEspera;

    public Restaurante() {
        totalMesas = new Mesa[]{
                new Mesa(4),
                new Mesa(4),
                new Mesa(4),
                new Mesa(4),
                new Mesa(6),
                new Mesa(6),
                new Mesa(6),
                new Mesa(6),
                new Mesa(8),
                new Mesa(8)
        };
        filaDeEspera = new FilaDeEspera();
    }

    public void chegadaCliente(Cliente cliente) {
        if (!atribuirMesa(cliente)) {
            colocarNaFilaEspera(cliente);
        }
    }

    public void colocarNaFilaEspera(Cliente cliente) {
        filaDeEspera.adicionarCliente(cliente);
    }

    public void tirarDaFilaEspera(Cliente cliente) {
        filaDeEspera.removerCliente(cliente);
    }

    public boolean atribuirMesa(Cliente cliente) {
        for (Mesa mesa : totalMesas) {
            if (mesa.verificarOcupacao() && cliente.getIdade() <= mesa.getTotalDeLugar()) {
                mesa.inserirCliente(cliente);
                return true;
            }
        }
        return false;
    }

    public void liberarMesa(Mesa mesa) {
        mesa.removerCliente(null); // Remover todos os clientes da mesa
    }

    public FilaDeEspera getFilaDeEspera() {
        return filaDeEspera;
    }
}

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o número de clientes na fila de espera: ");
        int numeroClientes = scanner.nextInt();

        for (int i = 0; i < numeroClientes; i++) {
            System.out.print("Informe o nome do cliente " + (i + 1) + ": ");
            String nome = scanner.next();
            System.out.print("Informe a idade do cliente " + (i + 1) + ": ");
            int idade = scanner.nextInt();
            System.out.print("Informe a data de entrada do cliente " + (i + 1) + " (DD/MM/AAAA): ");
            String dataEntrada = scanner.next();
            restaurante.chegadaCliente(new Cliente(nome, idade, dataEntrada));
        }

        scanner.close();

        System.out.println("Clientes na fila de espera: " + restaurante.getFilaDeEspera().tamanhoDaFila());
    }
}
