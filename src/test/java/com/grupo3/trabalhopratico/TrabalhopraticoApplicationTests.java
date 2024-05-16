package com.grupo3.trabalhopratico.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
