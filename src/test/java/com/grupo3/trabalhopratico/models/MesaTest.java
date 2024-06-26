import com.grupo3.trabalhopratico.models.Mesa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MesaTest {

    @Test
    public void testMesaConstructorAndMethods() {
        // Criando uma mesa com capacidade padrão
        Mesa mesa1 = new Mesa(1);
        assertEquals(4, mesa1.getCapacidade()); // Verifica se a capacidade padrão é 4
        assertFalse(mesa1.StatusMesa()); // Verifica se a mesa está inicialmente desocupada

        // Criando uma mesa com capacidade 6
        Mesa mesa2 = new Mesa(2);
        assertEquals(6, mesa2.getCapacidade()); // Verifica se a capacidade é 6
        assertFalse(mesa2.StatusMesa()); // Verifica se a mesa está inicialmente desocupada

        // Criando uma mesa com capacidade 8
        Mesa mesa3 = new Mesa(3);
        assertEquals(8, mesa3.getCapacidade()); // Verifica se a capacidade é 8
        assertFalse(mesa3.StatusMesa()); // Verifica se a mesa está inicialmente desocupada

        // Testando o método setStatus
        mesa1.setStatus(true);
        assertTrue(mesa1.StatusMesa()); // Verifica se a mesa foi marcada como ocupada

        // Testando o método setOcupada
        mesa1.setOcupada(false);
        assertFalse(mesa1.StatusMesa()); // Verifica se a mesa foi marcada como desocupada
    }

    @Test
    public void testMesaToString() {
        // Criando uma mesa
        Mesa mesa = new Mesa(1);
        mesa.setStatus(true); // Marcando a mesa como ocupada
        String expectedToString = "Id Mesa: 1, Capacidade: 4";
        assertEquals(expectedToString, mesa.toString()); // Verifica se o método toString retorna o valor esperado
    }
}
