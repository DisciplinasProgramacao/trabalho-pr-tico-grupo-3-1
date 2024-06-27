package com.grupo3.trabalhopratico.models;

import com.grupo3.trabalhopratico.models.Mesa;
import com.grupo3.trabalhopratico.models.Reserva;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MesaTest {

    @Test
    public void testMesaCreation() {
        Mesa mesa = new Mesa(4);

        assertEquals(4, mesa.getCapacidade());
        assertTrue(mesa.isDisponivel());
    }

    @Test
    public void testSetDisponivel() {
        Mesa mesa = new Mesa(4);
        mesa.setDisponivel(false);

        assertFalse(mesa.isDisponivel());
    }

    @Test
    public void testReserva() {
        Mesa mesa = new Mesa(4);
        Reserva reserva = new Reserva(4);

        mesa.setReserva(reserva);

        assertEquals(reserva, mesa.getReserva());
    }
}
