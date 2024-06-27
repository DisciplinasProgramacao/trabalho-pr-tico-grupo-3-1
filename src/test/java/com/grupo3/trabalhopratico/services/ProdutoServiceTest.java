package com.grupo3.trabalhopratico.services;

import com.grupo3.trabalhopratico.exceptions.DuplicateEntityException;
import com.grupo3.trabalhopratico.exceptions.EntityNotFoundException;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.repositories.ProdutoRepository;
import com.grupo3.trabalhopratico.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProdutos() {
        Produto produto = new Produto();
        when(produtoRepository.findAll()).thenReturn(List.of(produto));

        List<Produto> produtos = produtoService.getProdutos();
        assertEquals(1, produtos.size());
        assertEquals(produto, produtos.get(0));
    }

    @Test
    public void testAddNewProduto_DuplicateName() {
        Produto produto = new Produto("Pizza", 20.0, "prato", "Deliciosa pizza");
        when(produtoRepository.findProdutoByNome("Pizza")).thenReturn(Optional.of(produto));

        assertThrows(DuplicateEntityException.class, () -> produtoService.addNewProduto(produto));
    }

    @Test
    public void testAddNewProduto_Valid() {
        Produto produto = new Produto("Pizza", 20.0, "prato", "Deliciosa pizza");
        when(produtoRepository.findProdutoByNome("Pizza")).thenReturn(Optional.empty());
        when(produtoRepository.countByTipo("prato")).thenReturn(5L);

        produtoService.addNewProduto(produto);
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    public void testDeleteProduto_NotFound() {
        when(produtoRepository.findProdutoById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> produtoService.deleteProduto(1L));
    }

    @Test
    public void testDeleteProduto_Valid() {
        Produto produto = new Produto();
        when(produtoRepository.findProdutoById(1L)).thenReturn(Optional.of(produto));

        produtoService.deleteProduto(1L);
        verify(produtoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateProduto_NotFound() {
        when(produtoRepository.findProdutoById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> produtoService.updateProduto(1L, "Pizza", 25.0));
    }

    @Test
    public void testUpdateProduto_Valid() {
        Produto produto = new Produto();
        when(produtoRepository.findProdutoById(1L)).thenReturn(Optional.of(produto));

        produtoService.updateProduto(1L, "Pizza", 25.0);
        verify(produtoRepository, times(1)).save(produto);
        assertEquals("Pizza", produto.getNome());
        assertEquals(25.0, produto.getPreco());
    }
}
