package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProdutos() {
        List<Produto> produtos = Arrays.asList(
                new Produto(1L, "Produto A", 10.0, "bebida", "Descrição A"),
                new Produto(2L, "Produto B", 20.0, "comida", "Descrição B")
        );

        when(produtoService.getProdutos()).thenReturn(produtos);

        List<Produto> result = produtoController.getProdutos();

        assertEquals(2, result.size());
        assertEquals("Produto A", result.get(0).getNome());
        assertEquals(10.0, result.get(0).getPreco());
        assertEquals("Produto B", result.get(1).getNome());
        assertEquals(20.0, result.get(1).getPreco());

        verify(produtoService, times(1)).getProdutos();
    }

    @Test
    public void testRegisterNewProduto() {
        Produto produto = new Produto(3L, "Produto C", 30.0, "bebida", "Descrição C");

        doNothing().when(produtoService).addNewProduto(produto);

        produtoController.registerNewProduto(produto);

        verify(produtoService, times(1)).addNewProduto(produto);
    }

    @Test
    public void testDeleteProduto() {
        Long produtoId = 1L;

        doNothing().when(produtoService).deleteProduto(produtoId);

        produtoController.deleteProduto(produtoId);

        verify(produtoService, times(1)).deleteProduto(produtoId);
    }

    @Test
    public void testUpdateProduto() {
        Long produtoId = 1L;
        String novoNome = "Produto A Atualizado";
        double novoPreco = 15.0;

        doNothing().when(produtoService).updateProduto(produtoId, novoNome, novoPreco);

        produtoController.updateProduto(produtoId, novoNome, novoPreco);

        verify(produtoService, times(1)).updateProduto(produtoId, novoNome, novoPreco);
    }
}
