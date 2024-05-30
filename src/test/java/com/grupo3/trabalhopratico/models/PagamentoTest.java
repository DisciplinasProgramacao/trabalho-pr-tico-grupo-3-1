package com.grupo3.trabalhopratico.models;

import static org.junit.Assert.*;
import org.junit.Test;

public class PagamentoTest {

    @Test
    public void testConstructorAndGetters() {
        Pagamento pagamento = new Pagamento(1L, 100.0, 90.0, 80.0, 10.0, "Cartão", 20240529);
        
        assertEquals(1L, (long) pagamento.getId());
        assertEquals(100.0, pagamento.getValorBrutoTotal(), 0.01);
        assertEquals(90.0, pagamento.getValorLiquidoTotal(), 0.01);
        assertEquals(80.0, pagamento.getValorPago(), 0.01);
        assertEquals(10.0, pagamento.getValorDescontado(), 0.01);
        assertEquals("Cartão", pagamento.getMetodoPagamento());
        assertEquals(20240529, pagamento.getDataPagamento());
    }

    @Test
    public void testSetters() {
        Pagamento pagamento = new Pagamento(0, 0, 0, 0);
        
        pagamento.setId(1L);
        pagamento.setValorBrutoTotal(100.0);
        pagamento.setValorLiquidoTotal(90.0);
        pagamento.setValorPago(80.0);
        pagamento.setValorDescontado(10.0);
        pagamento.setMetodoPagamento("Cartão");
        pagamento.setDataPagamento(20240529);
        
        assertEquals(1L, (long) pagamento.getId());
        assertEquals(100.0, pagamento.getValorBrutoTotal(), 0.01);
        assertEquals(90.0, pagamento.getValorLiquidoTotal(), 0.01);
        assertEquals(80.0, pagamento.getValorPago(), 0.01);
        assertEquals(10.0, pagamento.getValorDescontado(), 0.01);
        assertEquals("Cartão", pagamento.getMetodoPagamento());
        assertEquals(20240529, pagamento.getDataPagamento());
    }
}
