import com.grupo3.trabalhopratico.models.Pagamento;
import com.grupo3.trabalhopratico.repositories.PagamentoRepository;
import com.grupo3.trabalhopratico.services.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPagamentosByDataAndMetodoPagamento() {
        // Mocking data
        LocalDate data = LocalDate.of(2024, 6, 28);
        String metodoPagamento = "Cartão";

        // Mocking repository response
        List<Pagamento> expectedPagamentos = Arrays.asList(
                new Pagamento(1L, 100.0, 0.0, "Cartão", data),
                new Pagamento(2L, 50.0, 0.0, "Cartão", data)
        );
        when(pagamentoRepository.findByDataPagamentoAndMetodoPagamento(data, metodoPagamento))
                .thenReturn(expectedPagamentos);

        // Calling service method
        List<Pagamento> pagamentos = pagamentoService.getPagamentos(data, metodoPagamento);

        // Verifying repository method was called once
        verify(pagamentoRepository, times(1)).findByDataPagamentoAndMetodoPagamento(data, metodoPagamento);

        // Asserting the result
        assertEquals(expectedPagamentos.size(), pagamentos.size());
        assertEquals(expectedPagamentos.get(0).getId(), pagamentos.get(0).getId());
        assertEquals(expectedPagamentos.get(1).getId(), pagamentos.get(1).getId());
    }

    @Test
    public void testGetPagamentosByData() {
        // Mocking data
        LocalDate data = LocalDate.of(2024, 6, 28);

        // Mocking repository response
        List<Pagamento> expectedPagamentos = Arrays.asList(
                new Pagamento(1L, 100.0, 0.0, "Cartão", data),
                new Pagamento(2L, 50.0, 0.0, "Dinheiro", data)
        );
        when(pagamentoRepository.findByDataPagamento(data))
                .thenReturn(expectedPagamentos);

        // Calling service method
        List<Pagamento> pagamentos = pagamentoService.getPagamentos(data, null);

        // Verifying repository method was called once
        verify(pagamentoRepository, times(1)).findByDataPagamento(data);

        // Asserting the result
        assertEquals(expectedPagamentos.size(), pagamentos.size());
        assertEquals(expectedPagamentos.get(0).getId(), pagamentos.get(0).getId());
        assertEquals(expectedPagamentos.get(1).getId(), pagamentos.get(1).getId());
    }

    // Testes para outros métodos de serviço aqui...

}
