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
	
}
