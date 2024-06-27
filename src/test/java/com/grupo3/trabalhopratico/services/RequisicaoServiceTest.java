package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.exceptions.EntityAlreadyClosedException;
import com.grupo3.trabalhopratico.exceptions.EntityNotFoundException;
import com.grupo3.trabalhopratico.models.Mesa;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.repositories.RequisicaoRepository;
import com.grupo3.trabalhopratico.services.MesaService;
import com.grupo3.trabalhopratico.services.RequisicaoService;
import com.grupo3.trabalhopratico.services.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RequisicaoServiceTest {

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @Mock
    private MesaService mesaService;

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private RequisicaoService requisicaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRequisicoesAtivas() {
        Requisicao requisicao = new Requisicao();
        when(requisicaoRepository.findRequisicoesAtivas()).thenReturn(List.of(requisicao));

        List<Requisicao> requisicoes = requisicaoService.getRequisicoesAtivas();
        assertEquals(1, requisicoes.size());
        assertEquals(requisicao, requisicoes.get(0));
    }

    @Test
    public void testAddNewRequisicao() {
        Requisicao requisicao = new Requisicao();
        requisicao.setNumeroPessoas(4);
        Mesa mesa = new Mesa(4);
        when(mesaService.findAvailableMesa(4)).thenReturn(Optional.of(mesa));

        requisicaoService.addNewRequisicao(requisicao);

        assertTrue(requisicao.isEmAtendimento());
        assertEquals(mesa, requisicao.getMesa());
        verify(mesaService, times(1)).save(mesa);
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testGetRequisicao_NotFound() {
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> requisicaoService.getRequisicao(1L));
    }

    @Test
    public void testAddProdutosToRequisicao() {
        Requisicao requisicao = new Requisicao();
        Produto produto = new Produto();
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicao));

        requisicaoService.addProdutosToRequisicao(1L, List.of(produto));
        assertEquals(1, requisicao.getProdutos().size());
        assertEquals(produto, requisicao.getProdutos().get(0));
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testCloseRequisicao() {
        Requisicao requisicao = new Requisicao();
        requisicao.setAtiva(true);
        Mesa mesa = new Mesa(4);
        requisicao.setMesa(mesa);
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicao));

        requisicaoService.closeRequisicao(1L);

        assertFalse(requisicao.isAtiva());
        assertNotNull(requisicao.getHoraSaida());
        assertTrue(mesa.isDisponivel());
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testCloseRequisicao_AlreadyClosed() {
        Requisicao requisicao = new Requisicao();
        requisicao.setAtiva(false);
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicao));

        assertThrows(EntityAlreadyClosedException.class, () -> requisicaoService.closeRequisicao(1L));
    }
}
