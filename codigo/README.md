# Código do Projeto

import java.util.ArrayList;
import java.util.Scanner;

class Cliente {
    private int pessoasTotal;

    public Cliente(int pessoasTotal) {
        this.pessoasTotal = pessoasTotal;
    }

    public int getPessoasTotal() {
        return pessoasTotal;
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
        return !ocupacao;
    }

    public int getTotalDeLugar() {
        return totalDeLugar;
    }

    public boolean temEspacoParaCliente(Cliente cliente) {
        return totalDeLugar >= cliente.getPessoasTotal();
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
        if (cliente.getPessoasTotal() > 8) {
            for (Mesa mesa : totalMesas) {
                if (mesa.verificarOcupacao() && mesa.temEspacoParaCliente(cliente)) {
                    mesa.inserirCliente(cliente);
                    return;
                }
            }
            colocarNaFilaEspera(cliente);
        } else {
            if (!atribuirMesa(cliente)) {
                colocarNaFilaEspera(cliente);
            }
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
            if (mesa.verificarOcupacao() && cliente.getPessoasTotal() <= mesa.getTotalDeLugar()) {
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
            System.out.print("Informe o número de pessoas do cliente " + (i + 1) + ": ");
            int numeroPessoas = scanner.nextInt();
            restaurante.chegadaCliente(new Cliente(numeroPessoas));
        }

        scanner.close();

        System.out.println("Clientes na fila de espera: " + restaurante.getFilaDeEspera().tamanhoDaFila());
    }
}
