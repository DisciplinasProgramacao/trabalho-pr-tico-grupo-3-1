import com.grupo3.trabalhopratico.models.Cliente;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.repositories.RequisicaoRepository;
import com.grupo3.trabalhopratico.services.RequisicaoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RequisicaoTest {

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @InjectMocks
    private RequisicaoService requisicaoService;

    private Validator validator;
    private Cliente clienteValido;
    private List<Produto> produtosValidos;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        clienteValido = new Cliente("João da Silva");
        produtosValidos = new ArrayList<>();
        produtosValidos.add(new Produto("Produto Teste", 10.0, "Comida"));
    }

    @Test
    public void testRequisicaoValida() {
        Requisicao requisicao = new Requisicao(1L, "João da Silva", 5, true, false, produtosValidos, clienteValido);

        when(requisicaoRepository.save(requisicao)).thenReturn(requisicao);

        requisicaoService.addNewRequisicao(requisicao);

        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testNomeClienteEmBranco() {
        Requisicao requisicao = new Requisicao(1L, "", 5, true, false, produtosValidos, clienteValido);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        ConstraintViolation<Requisicao> violation = violations.iterator().next();
        assertEquals("O nome do cliente é obrigatório", violation.getMessage(), "A mensagem de erro deve ser 'O nome do cliente é obrigatório'");
    }

    @Test
    public void testNomeClienteCurto() {
        Requisicao requisicao = new Requisicao(1L, "Jo", 5, true, false, produtosValidos, clienteValido);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        ConstraintViolation<Requisicao> violation = violations.iterator().next();
        assertEquals("O nome do cliente deve ter entre 3 e 50 caracteres", violation.getMessage(), "A mensagem de erro deve ser 'O nome do cliente deve ter entre 3 e 50 caracteres'");
    }

    @Test
    public void testNumeroPessoasMenorQueUm() {
        Requisicao requisicao = new Requisicao(1L, "João da Silva", 0, true, false, produtosValidos, clienteValido);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        ConstraintViolation<Requisicao> violation = violations.iterator().next();
        assertEquals("O número de pessoas deve ser maior que 0", violation.getMessage(), "A mensagem de erro deve ser 'O número de pessoas deve ser maior que 0'");
    }

    @Test
    public void testNumeroPessoasMaiorQueDez() {
        Requisicao requisicao = new Requisicao(1L, "João da Silva", 11, true, false, produtosValidos, clienteValido);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        ConstraintViolation<Requisicao> violation = violations.iterator().next();
        assertEquals("O número de pessoas deve ser menor ou igual a 10", violation.getMessage(), "A mensagem de erro deve ser 'O número de pessoas deve ser menor ou igual a 10'");
    }

    @Test
    public void testClienteNulo() {
        Requisicao requisicao = new Requisicao(1L, "João da Silva", 5, true, false, produtosValidos, null);

        Set<ConstraintViolation<Requisicao>> violations = validator.validate(requisicao);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        ConstraintViolation<Requisicao> violation = violations.iterator().next();
        assertEquals("É obrigatório ter um cliente na requisição", violation.getMessage(), "A mensagem de erro deve ser 'É obrigatório ter um cliente na requisição'");
    }
}
