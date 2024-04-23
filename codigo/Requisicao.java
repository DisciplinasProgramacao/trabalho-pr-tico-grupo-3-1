import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Requisicao {
    private List<Mesa> mesas;
    private List<Reserva> filaDeEspera;

    public Requisicao(int totalMesas, int capacidade4, int capacidade6, int capacidade8) {
        mesas = new ArrayList<>();
        filaDeEspera = new ArrayList<>();

        for (int i = 0; i < capacidade4; i++) {
            mesas.add(new Mesa(4));
        }
        for (int i = 0; i < capacidade6; i++) {
            mesas.add(new Mesa(6));
        }
        for (int i = 0; i < capacidade8; i++) {
            mesas.add(new Mesa(8));
        }
    }

    public void fazerReserva(int quantidadePessoas) {
        Reserva reserva = new Reserva(quantidadePessoas);
        
        List<Mesa> mesasDisponiveis = mesas.stream()
                .filter(m -> m.getCapacidade() >= quantidadePessoas)
                .filter(m -> m.estaDisponivel(reserva))
                .sorted(Comparator.comparingInt(Mesa::getCapacidade))
                .collect(Collectors.toList());

        if (!mesasDisponiveis.isEmpty()) {
            mesasDisponiveis.get(0).alocarReserva(reserva);
        } else {
            filaDeEspera.add(reserva);
        }
    }

    public void concluirReserva(int quantidadePessoas) {
        for (Mesa mesa : mesas) {
            if (mesa.concluirReserva(quantidadePessoas)) {
                break;
            }
        }
        preencherMesasDaFilaDeEspera();
    }

    private void preencherMesasDaFilaDeEspera() {
        List<Reserva> removidos = new ArrayList<>();
        for (Reserva reserva : filaDeEspera) {
            fazerReserva(reserva.getQuantidadePessoas());
            removidos.add(reserva);
        }
        filaDeEspera.removeAll(removidos);
    }

    public void exibirMesas() {
        System.out.println("Mesas:");
        for (int i = 0; i < mesas.size(); i++) {
            System.out.println("Mesa " + (i + 1) + ": " + mesas.get(i));
        }

        System.out.println("\nFila de Espera:");
        for (Reserva reserva : filaDeEspera) {
            System.out.println(reserva);
        }
    }
}
