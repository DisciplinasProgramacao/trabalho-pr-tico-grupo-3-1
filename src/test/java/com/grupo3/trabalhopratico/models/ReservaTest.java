package com.grupo3.trabalhopratico.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    @Test
    public void testConstructor() {
        Reserva reserva = new Reserva(5);
        assertNotNull(reserva);
        assertEquals(5, reserva.getQuantidadePessoas());
    }

    @Test
    public void testGetQuantidadePessoas() {
        Reserva reserva = new Reserva(3);
        assertEquals(3, reserva.getQuantidadePessoas());
    }

    @Test
    public void testToString() {
        Reserva reserva = new Reserva(4);
        assertEquals("Reserva para 4 pessoas", reserva.toString());
    }
}