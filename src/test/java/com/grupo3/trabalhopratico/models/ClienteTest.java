package com.grupo3.trabalhopratico.models;

import com.grupo3.trabalhopratico.models.Cliente;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    private final Validator validator;

    public ClienteTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidCliente() {
        Cliente cliente = new Cliente("Rogerio");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNomeNotBlank() {
        Cliente cliente = new Cliente("");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertEquals(1, violations.size());
        assertEquals("O nome do cliente é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    public void testNomeSize() {
        Cliente cliente = new Cliente("Jo");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertEquals(1, violations.size());
        assertEquals("O nome do cliente deve ter entre 3 e 50 caracteres", violations.iterator().next().getMessage());
    }
}
