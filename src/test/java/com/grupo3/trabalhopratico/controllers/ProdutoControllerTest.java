package com.grupo3.trabalhopratico.controllers;

import com.grupo3.trabalhopratico.controllers.ProdutoController;
import com.grupo3.trabalhopratico.exceptions.DuplicateEntityException;
import com.grupo3.trabalhopratico.exceptions.EntityNotFoundException;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
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
        Produto produto = new Produto();
        List<Produto> produtos = Collections.singletonList(produto);

        when(produtoService.getProdutos()).thenReturn(produtos);

        List<Produto> response = produtoController.getProdutos();

        assertEquals(1, response.size());
        assertEquals(produto, response.get(0));
    }

    @Test
    public void testRegisterNewProduto() {
        Produto produto = new Produto();
        ResponseEntity<String> response = produtoController.registerNewProduto(produto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Produto criado com sucesso.", response.getBody());
        verify(produtoService, times(1)).addNewProduto(produto);
    }

    @Test
    public void testRegisterNewProduto_DuplicateEntityException() {
        Produto produto = new Produto();

        doThrow(new DuplicateEntityException("Produto já existe")).when(produtoService).addNewProduto(produto);

        ResponseEntity<String> response = produtoController.registerNewProduto(produto);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Produto já existe", response.getBody());
    }

    @Test
    public void testDeleteProduto() {
        Long produtoId = 1L;
        ResponseEntity<String> response = produtoController.deleteProduto(produtoId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Produto excluído com sucesso.", response.getBody());
        verify(produtoService, times(1)).deleteProduto(produtoId);
    }

    @Test
    public void testDeleteProduto_EntityNotFoundException() {
        Long produtoId = 1L;

        doThrow(new EntityNotFoundException("Produto não encontrado.")).when(produtoService).deleteProduto(produtoId);

        ResponseEntity<String> response = produtoController.deleteProduto(produtoId);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Produto não encontrado.", response.getBody());
    }

    @Test
    public void testUpdateProduto() {
        Long produtoId = 1L;
        Produto produtoDetails = new Produto();
        produtoDetails.setNome("Nome Atualizado");
        produtoDetails.setPreco(20.0);

        ResponseEntity<String> response = produtoController.updateProduto(produtoId, produtoDetails);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Produto atualizado com sucesso.", response.getBody());
        verify(produtoService, times(1)).updateProduto(produtoId, "Nome Atualizado", 20.0);
    }

    @Test
    public void testUpdateProduto_EntityNotFoundException() {
        Long produtoId = 1L;
        Produto produtoDetails = new Produto();

        doThrow(new EntityNotFoundException("Produto não encontrado.")).when(produtoService).updateProduto(produtoId, produtoDetails.getNome(), produtoDetails.getPreco());

        ResponseEntity<String> response = produtoController.updateProduto(produtoId, produtoDetails);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Produto não encontrado.", response.getBody());
    }
}
