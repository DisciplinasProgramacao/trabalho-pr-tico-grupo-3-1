package com.grupo3.trabalhopratico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrabalhopraticoApplicationTests {

	@Before
    public void setUp() {
        requisicao = new Requisicao(3, 2, 1, 1); // Total de 3 mesas: 2 de 4 lugares, 1 de 6 lugares, 1 de 8 lugares
    }
	@Test
	void contextLoads() {
	}
  @Test
    public void testFazerReserva() {
       
        requisicao.fazerReserva(4);
        assertEquals(1, requisicao.getMesas().get(0).getQuantidadeReservas());  
        requisicao.fazerReserva(5);
        assertEquals(1, requisicao.getFilaDeEspera().size());
    }

	public void testConcluirReserva() {
        requisicao.fazerReserva(4);
        requisicao.concluirReserva(4);
        assertEquals(0, requisicao.getMesas().get(0).getQuantidadeReservas());
        requisicao.fazerReserva(6);
        requisicao.concluirReserva(6);
        assertEquals(0, requisicao.getMesas().get(1).getQuantidadeReservas());
    }

 @Test
    public void testExibirMesas() {
        requisicao.fazerReserva(4);
        requisicao.fazerReserva(6);
        requisicao.fazerReserva(8);
        requisicao.fazerReserva(5); 
        requisicao.exibirMesas();
    }

public class ProdutoTest {

    @Test
    public void testGetId() {
        Produto produto = new Produto(1L, "Produto A", 10.50);
        assertEquals(1L, produto.getId().longValue());
    }

    @Test
    public void testSetId() {
        Produto produto = new Produto();
        produto.setId(1L);
        assertEquals(1L, produto.getId().longValue());
    }

    @Test
    public void testGetNome() {
        Produto produto = new Produto(1L, "Produto A", 10.50);
        assertEquals("Produto A", produto.getNome());
    }

    @Test
    public void testSetNome() {
        Produto produto = new Produto();
        produto.setNome("Produto B");
        assertEquals("Produto B", produto.getNome());
    }

    @Test
    public void testGetPreco() {
        Produto produto = new Produto(1L, "Produto A", 10.50);
        assertEquals(10.50, produto.getPreco(), 0.001);
    }

    @Test
    public void testSetPreco() {
        Produto produto = new Produto();
        produto.setPreco(20.75);
        assertEquals(20.75, produto.getPreco(), 0.001);
    }

    @Test
    public void testToString() {
        Produto produto = new Produto(1L, "Produto A", 10.50);
        assertEquals("Produto{id=1, nome='Produto A', preco=10.5}", produto.toString());
    }
}
