import com.grupo3.trabalhopratico.models.Cliente;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testClienteNomeValido() {
        Cliente cliente = new Cliente("João da Silva");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertTrue(violations.isEmpty(), "Não deve haver violações de validação");
    }

    @Test
    public void testClienteNomeEmBranco() {
        Cliente cliente = new Cliente("");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        ConstraintViolation<Cliente> violation = violations.iterator().next();
        assertEquals("O nome do cliente é obrigatório", violation.getMessage(), "A mensagem de erro deve ser 'O nome do cliente é obrigatório'");
    }

    @Test
    public void testClienteNomeCurto() {
        Cliente cliente = new Cliente("Al");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        ConstraintViolation<Cliente> violation = violations.iterator().next();
        assertEquals("O nome do cliente deve ter entre 3 e 50 caracteres", violation.getMessage(), "A mensagem de erro deve ser 'O nome do cliente deve ter entre 3 e 50 caracteres'");
    }

    @Test
    public void testClienteNomeLongo() {
        Cliente cliente = new Cliente("Cliente com um nome muito longo que excede o limite permitido pelo sistema de validação");

        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        ConstraintViolation<Cliente> violation = violations.iterator().next();
        assertEquals("O nome do cliente deve ter entre 3 e 50 caracteres", violation.getMessage(), "A mensagem de erro deve ser 'O nome do cliente deve ter entre 3 e 50 caracteres'");
    }
}
