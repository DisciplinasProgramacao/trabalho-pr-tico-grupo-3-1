package com.grupo3.trabalhopratico.models;

import com.grupo3.trabalhopratico.models.Produto;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoTest {

    private final Validator validator;

    public ProdutoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidProduto() {
        Produto produto = new Produto("Pizza", 20.0, "Prato", "Deliciosa pizza");

        Set<ConstraintViolation<Produto>> violations = validator.validate(produto);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNomeNotBlank() {
        Produto produto = new Produto("", 20.0, "Prato", "Deliciosa pizza");

        Set<ConstraintViolation<Produto>> violations = validator.validate(produto);
        assertEquals(1, violations.size());
        assertEquals("O nome do produto é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    public void testPrecoMin() {
        Produto produto = new Produto("Pizza", -1.0, "Prato", "Deliciosa pizza");

        Set<ConstraintViolation<Produto>> violations = validator.validate(produto);
        assertEquals(1, violations.size());
        assertEquals("O preço deve ser maior que zero", violations.iterator().next().getMessage());
    }
}
