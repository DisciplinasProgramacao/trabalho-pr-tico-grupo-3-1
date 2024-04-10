package trabalhoPratico1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {

	@Test
    public void testInserirCliente() {
        Mesa mesa = new Mesa(4);
        Cliente cliente = new Cliente("João", 30, "01/01/2024");
        mesa.inserirCliente(cliente);
        assertTrue(mesa.verificarOcupacao());
    }

    @Test
    public void testRemoverCliente() {
        Mesa mesa = new Mesa(4);
        Cliente cliente = new Cliente("Maria", 25, "01/01/2024");
        mesa.inserirCliente(cliente);
        mesa.removerCliente(cliente);
        assertFalse(mesa.verificarOcupacao());
    }
    @Test
    public void testAtribuirMesa() {
        Restaurante restaurante = new Restaurante();
        Cliente cliente = new Cliente("José", 50, "01/01/2024");
        assertTrue(restaurante.atribuirMesa(cliente));
    }

    @Test
    public void testColocarNaFilaEspera() {
        Restaurante restaurante = new Restaurante();
        Cliente cliente = new Cliente("Carla", 20, "01/01/2024");
        restaurante.colocarNaFilaEspera(cliente);
        assertEquals(1, restaurante.getFilaDeEspera().tamanhoDaFila());
    }
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
