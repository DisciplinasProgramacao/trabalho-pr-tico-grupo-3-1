import com.grupo3.trabalhopratico.exceptions.EntityAlreadyClosedException;
import com.grupo3.trabalhopratico.exceptions.EntityNotFoundException;
import com.grupo3.trabalhopratico.exceptions.PreconditionFailedException;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.repositories.RequisicaoRepository;
import com.grupo3.trabalhopratico.services.RequisicaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RequisicaoServiceTest {

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @InjectMocks
    private RequisicaoService requisicaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRequisicoesAtivas() {
        // Mocking repository response
        List<Requisicao> expectedRequisicoes = new ArrayList<>();
        expectedRequisicoes.add(new Requisicao(1L, "Cliente A", 3, true, false, new ArrayList<>(), null));
        expectedRequisicoes.add(new Requisicao(2L, "Cliente B", 2, true, true, new ArrayList<>(), null));
        when(requisicaoRepository.findByAtiva(true)).thenReturn(expectedRequisicoes);

        // Calling service method
        List<Requisicao> requisicoes = requisicaoService.getRequisicoesAtivas();

        // Verifying repository method was called once
        verify(requisicaoRepository, times(1)).findByAtiva(true);

        // Asserting the result
        assertEquals(expectedRequisicoes.size(), requisicoes.size());
        assertEquals(expectedRequisicoes.get(0).getId(), requisicoes.get(0).getId());
        assertEquals(expectedRequisicoes.get(1).getId(), requisicoes.get(1).getId());
    }

    @Test
    public void testGetRequisicoesByTipoEmAtendimento() {
        // Mocking repository response
        List<Requisicao> expectedRequisicoes = new ArrayList<>();
        expectedRequisicoes.add(new Requisicao(1L, "Cliente A", 3, true, true, new ArrayList<>(), null));
        when(requisicaoRepository.findByEmAtendimento(true)).thenReturn(expectedRequisicoes);

        // Calling service method
        List<Requisicao> requisicoes = requisicaoService.getRequisicoesByTipo("EM_ATENDIMENTO");

        // Verifying repository method was called once
        verify(requisicaoRepository, times(1)).findByEmAtendimento(true);

        // Asserting the result
        assertEquals(expectedRequisicoes.size(), requisicoes.size());
        assertEquals(expectedRequisicoes.get(0).getId(), requisicoes.get(0).getId());
    }

    @Test
    public void testGetRequisicoesByTipoNaFila() {
        // Mocking repository response
        List<Requisicao> expectedRequisicoes = new ArrayList<>();
        expectedRequisicoes.add(new Requisicao(2L, "Cliente B", 2, true, false, new ArrayList<>(), null));
        when(requisicaoRepository.findByEmAtendimento(false)).thenReturn(expectedRequisicoes);

        // Calling service method
        List<Requisicao> requisicoes = requisicaoService.getRequisicoesByTipo("NA_FILA");

        // Verifying repository method was called once
        verify(requisicaoRepository, times(1)).findByEmAtendimento(false);

        // Asserting the result
        assertEquals(expectedRequisicoes.size(), requisicoes.size());
        assertEquals(expectedRequisicoes.get(0).getId(), requisicoes.get(0).getId());
    }

    @Test
    public void testGetRequisicoesByTipoInvalido() {
        // Calling service method and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> requisicaoService.getRequisicoesByTipo("INVALIDO"));
    }

    @Test
    public void testAddNewRequisicao() {
        // Mocking a new requisicao
        Requisicao novaRequisicao = new Requisicao(null, "Novo Cliente", 5, true, false, new ArrayList<>(), null);

        // Mocking repository save method
        when(requisicaoRepository.save(novaRequisicao)).thenReturn(novaRequisicao);

        // Calling service method
        requisicaoService.addNewRequisicao(novaRequisicao);

        // Verifying repository method was called once
        verify(requisicaoRepository, times(1)).save(novaRequisicao);
    }

    @Test
    public void testGetRequisicao() {
        // Mocking existing requisicao
        Requisicao requisicaoExistente = new Requisicao(1L, "Cliente Existente", 4, true, true, new ArrayList<>(), null);

        // Mocking repository response for findById
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicaoExistente));

        // Calling service method
        Requisicao requisicao = requisicaoService.getRequisicao(1L);

        // Verifying repository method was called once
        verify(requisicaoRepository, times(1)).findById(1L);

        // Asserting the result
        assertNotNull(requisicao);
        assertEquals(requisicaoExistente.getId(), requisicao.getId());
    }

    @Test
    public void testGetRequisicaoNotFound() {
        // Mocking non-existing requisicao ID
        Long requisicaoId = 999L;

        // Mocking repository response for findById
        when(requisicaoRepository.findById(requisicaoId)).thenReturn(Optional.empty());

        // Calling service method and expecting an exception
        assertThrows(EntityNotFoundException.class, () -> requisicaoService.getRequisicao(requisicaoId));
    }

    @Test
    public void testAddProdutosToRequisicao() {
        // Mocking existing requisicao
        Requisicao requisicaoExistente = new Requisicao(1L, "Cliente Existente", 4, true, true, new ArrayList<>(), null);

        // Mocking repository response for findById
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicaoExistente));

        // Mocking repository save method
        when(requisicaoRepository.save(requisicaoExistente)).thenReturn(requisicaoExistente);

        // Mocking produtos to add
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto A", 10.0, "Bebida", "Descrição do produto A"));
        produtos.add(new Produto(2L, "Produto B", 15.0, "Comida", "Descrição do produto B"));

        // Calling service method
        requisicaoService.addProdutosToRequisicao(1L, produtos);

        // Verifying repository method was called once
        verify(requisicaoRepository, times(1)).save(requisicaoExistente);

        // Verifying changes
        assertEquals(produtos.size(), requisicaoExistente.getProdutos().size());
    }

    @Test
    public void testAddProdutosToRequisicaoRequisicaoNaoEmAtendimento() {
        // Mocking existing requisicao not in emAtendimento state
        Requisicao requisicaoExistente = new Requisicao(1L, "Cliente Existente", 4, true, false, new ArrayList<>(), null);

        // Mocking repository response for findById
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicaoExistente));

        // Mocking produtos to add
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto A", 10.0, "Bebida", "Descrição do produto A"));
        produtos.add(new Produto(2L, "Produto B", 15.0, "Comida", "Descrição do produto B"));

        // Calling service method and expecting an exception
        assertThrows(PreconditionFailedException.class, () -> requisicaoService.addProdutosToRequisicao(1L, produtos));

        // Verifying repository save method was not called
        verify(requisicaoRepository, never()).save(any());
    }

    @Test
    public void testAddProdutosToRequisicaoRequisicaoNaoAtiva() {
        // Mocking existing requisicao not active
        Requisicao requisicaoExistente = new Requisicao(1L, "Cliente Existente", 4, false, true, new ArrayList<>(), null);

        // Mocking repository response for findById
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicaoExistente));

        // Mocking produtos to add
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto A", 10.0, "Bebida", "Descrição do produto A"));
        produtos.add(new Produto(2L, "Produto B", 15.0, "Comida", "Descrição do produto B"));

        // Calling service method and expecting an exception
        assertThrows(PreconditionFailedException.class, () -> requisicaoService.addProdutosToRequisicao(1L, produtos));

        // Verifying repository save method was not called
        verify(requisicaoRepository, never()).save(any());
    }

    @Test
    public void testCloseRequisicao() {
        // Mocking existing requisicao
        Requisicao requisicaoExistente = new Requisicao(1L, "Cliente Existente", 4, true, true, new ArrayList<>(), null);

        // Mocking repository response for findById
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicaoExistente));

        // Mocking repository save method
        when(requisicaoRepository.save(requisicaoExistente)).thenReturn(requisicaoExistente);

        // Calling service method
        requisicaoService.closeRequisicao(1L);

        // Verifying repository method was called once
        verify(requisicaoRepository, times(1)).save(requisicaoExistente);

        // Verifying changes
        assertFalse(requisicaoExistente.isAtiva());
    }

    @Test
    public void testCloseRequisicaoRequisicaoNaoAtiva() {
        // Mocking existing requisicao already closed
        Requisicao requisicaoExistente = new Requisicao(1L, "Cliente Existente", 4, false, true, new ArrayList<>(), null);

        // Mocking repository response for findById
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicaoExistente));

        // Calling service method and expecting an exception
        assertThrows(EntityAlreadyClosedException.class, () -> requisicaoService.closeRequisicao(1L));

        // Verifying repository save method was not called
        verify(requisicaoRepository, never()).save(any());
    }

    @Test
    public void testCloseRequisicaoRequisicaoNaoEmAtendimento() {
        // Mocking existing requisicao not in emAtendimento state
        Requisicao requisicaoExistente = new Requisicao(1L, "Cliente Existente", 4, true, false, new ArrayList<>(), null);

        // Mocking repository response for findById
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicaoExistente));

        // Calling service method and expecting an exception
        assertThrows(PreconditionFailedException.class, () -> requisicaoService.closeRequisicao(1L));

        // Verifying repository save method was not called
        verify(requisicaoRepository, never()).save(any());
    }
}

