import com.grupo3.trabalhopratico.models.Pagamento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PagamentoTest {

    @Test
    public void testValidPagamento() {
        Pagamento pagamento = new Pagamento(null, 100.0, 10.0, "Cartão de Crédito", LocalDate.now());

        // Validando os campos manualmente
        assertAll("pagamento",
                () -> assertNotNull(pagamento.getId(), "ID não deve ser nulo"),
                () -> assertEquals(100.0, pagamento.getValorPago(), "Valor pago deve ser 100.0"),
                () -> assertEquals(10.0, pagamento.getValorDescontado(), "Valor descontado deve ser 10.0"),
                () -> assertEquals("Cartão de Crédito", pagamento.getMetodoPagamento(), "Método de pagamento deve ser 'Cartão de Crédito'"),
                () -> assertNotNull(pagamento.getDataPagamento(), "Data de pagamento não deve ser nula")
        );
    }

    @Test
    public void testInvalidPagamento() {
        Pagamento pagamento = new Pagamento(null, 0.0, 10.0, null, null);

        // Validando campos com valores inválidos
        assertAll("pagamento inválido",
                () -> assertNull(pagamento.getId(), "ID deve ser nulo"),
                () -> assertEquals(0.0, pagamento.getValorPago(), "Valor pago deve ser 0.0"),
                () -> assertEquals(10.0, pagamento.getValorDescontado(), "Valor descontado deve ser 10.0"),
                () -> assertNull(pagamento.getMetodoPagamento(), "Método de pagamento deve ser nulo"),
                () -> assertNull(pagamento.getDataPagamento(), "Data de pagamento deve ser nula")
        );
    }

    @Test
    public void testGettersAndSetters() {
        Pagamento pagamento = new Pagamento(null, 0.0, 0.0, null, null);

        // Testando os métodos setters
        pagamento.setId(1L);
        pagamento.setValorPago(100.0);
        pagamento.setValorDescontado(10.0);
        pagamento.setMetodoPagamento("Cartão de Débito");
        pagamento.setDataPagamento(LocalDate.now());

        // Testando os métodos getters
        assertEquals(1L, pagamento.getId());
        assertEquals(100.0, pagamento.getValorPago());
        assertEquals(10.0, pagamento.getValorDescontado());
        assertEquals("Cartão de Débito", pagamento.getMetodoPagamento());
        assertEquals(LocalDate.now(), pagamento.getDataPagamento());
    }
}
