package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.controllers.RequisicaoController;
import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.services.RequisicaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RequisicaoControllerTest {

    @Mock
    private RequisicaoService requisicaoService;

    @InjectMocks
    private RequisicaoController requisicaoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRequisicoes() {
        Requisicao requisicao = new Requisicao();
        List<Requisicao> requisicoes = Collections.singletonList(requisicao);

        when(requisicaoService.getRequisicoesAtivas()).thenReturn(requisicoes);

        ResponseEntity<List<Requisicao>> response = requisicaoController.getRequisicoes(Optional.empty());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(requisicao, response.getBody().get(0));
    }

    @Test
    public void testRegisterNewRequisicao() {
        Requisicao requisicao = new Requisicao();
        ResponseEntity<String> response = requisicaoController.registerNewRequisicao(requisicao);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Requisição criada com sucesso e alocada na mesa.", response.getBody());
        verify(requisicaoService, times(1)).addNewRequisicao(requisicao);
    }

    @Test
    public void testGetRequisicao() {
        Long requisicaoId = 1L;
        Requisicao requisicao = new Requisicao();

        when(requisicaoService.getRequisicao(requisicaoId)).thenReturn(requisicao);

        ResponseEntity<Requisicao> response = requisicaoController.getRequisicao(requisicaoId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(requisicao, response.getBody());
    }

    @Test
    public void testAddProdutosToRequisicao() {
        Long requisicaoId = 1L;
        List<Produto> produtos = Collections.singletonList(new Produto());

        ResponseEntity<String> response = requisicaoController.addProdutosToRequisicao(requisicaoId, produtos);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Produtos adicionados à requisição com sucesso", response.getBody());
        verify(requisicaoService, times(1)).addProdutosToRequisicao(requisicaoId, produtos);
    }

    @Test
    public void testSetRequisicaoEmAtendimento() {
        Long requisicaoId = 1L;

        ResponseEntity<String> response = requisicaoController.setRequisicaoEmAtendimento(requisicaoId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Requisição marcada como em atendimento", response.getBody());
        verify(requisicaoService, times(1)).setRequisicaoEmAtendimento(requisicaoId);
    }

    @Test
    public void testDeleteRequisicao() {
        Long requisicaoId = 1L;

        ResponseEntity<String> response = requisicaoController.deleteRequisicao(requisicaoId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Requisição encerrada e mesa liberada com sucesso", response.getBody());
        verify(requisicaoService, times(1)).closeRequisicao(requisicaoId);
    }
}
