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

import java.util.Arrays;
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
        // Mocking repository response
        List<Produto> expectedProdutos = Arrays.asList(
                new Produto(1L, "Produto 1", 10.0, "Bebida", "Descrição do produto 1"),
                new Produto(2L, "Produto 2", 15.0, "Comida", "Descrição do produto 2")
        );
        when(produtoRepository.findAll()).thenReturn(expectedProdutos);

        // Calling service method
        List<Produto> produtos = produtoService.getProdutos();

        // Verifying repository method was called once
        verify(produtoRepository, times(1)).findAll();

        // Asserting the result
        assertEquals(expectedProdutos.size(), produtos.size());
        assertEquals(expectedProdutos.get(0).getId(), produtos.get(0).getId());
        assertEquals(expectedProdutos.get(1).getId(), produtos.get(1).getId());
    }

    @Test
    public void testAddNewProduto() {
        // Mocking a new produto
        Produto novoProduto = new Produto(null, "Novo Produto", 20.0, "Bebida", "Descrição do novo produto");

        // Mocking repository response for findProdutoByNome
        when(produtoRepository.findProdutoByNome("Novo Produto")).thenReturn(Optional.empty());

        // Mocking repository save method
        when(produtoRepository.save(novoProduto)).thenReturn(novoProduto);

        // Calling service method
        produtoService.addNewProduto(novoProduto);

        // Verifying repository method was called once
        verify(produtoRepository, times(1)).save(novoProduto);
    }

    @Test
    public void testAddNewProdutoDuplicateName() {
        // Mocking a new produto with a duplicate name
        Produto produtoExistente = new Produto(1L, "Produto Existente", 30.0, "Comida", "Descrição do produto existente");

        // Mocking repository response for findProdutoByNome
        when(produtoRepository.findProdutoByNome("Produto Existente")).thenReturn(Optional.of(produtoExistente));

        // Calling service method and expecting an exception
        assertThrows(DuplicateEntityException.class, () -> produtoService.addNewProduto(produtoExistente));

        // Verifying repository method was not called
        verify(produtoRepository, never()).save(produtoExistente);
    }

    @Test
    public void testDeleteProduto() {
        // Mocking existing produto ID
        Long produtoId = 1L;

        // Mocking repository response for existsById
        when(produtoRepository.existsById(produtoId)).thenReturn(true);

        // Calling service method
        produtoService.deleteProduto(produtoId);

        // Verifying repository method was called once
        verify(produtoRepository, times(1)).deleteById(produtoId);
    }

    @Test
    public void testDeleteProdutoNotFound() {
        // Mocking non-existing produto ID
        Long produtoId = 999L;

        // Mocking repository response for existsById
        when(produtoRepository.existsById(produtoId)).thenReturn(false);

        // Calling service method and expecting an exception
        assertThrows(EntityNotFoundException.class, () -> produtoService.deleteProduto(produtoId));

        // Verifying repository method was not called
        verify(produtoRepository, never()).deleteById(produtoId);
    }

    @Test
    public void testUpdateProduto() {
        // Mocking existing produto
        Produto produtoExistente = new Produto(1L, "Produto Existente", 30.0, "Comida", "Descrição do produto existente");

        // Mocking repository response for findById
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoExistente));

        // Calling service method
        produtoService.updateProduto(1L, "Novo Nome", 40.0);

        // Verifying repository save method was called once
        verify(produtoRepository, times(1)).save(produtoExistente);

        // Verifying changes
        assertEquals("Novo Nome", produtoExistente.getNome());
        assertEquals(40.0, produtoExistente.getPreco());
    }

    @Test
    public void testUpdateProdutoNotFound() {
        // Mocking non-existing produto ID
        Long produtoId = 999L;

        // Mocking repository response for findById
        when(produtoRepository.findById(produtoId)).thenReturn(Optional.empty());

        // Calling service method and expecting an exception
        assertThrows(EntityNotFoundException.class, () -> produtoService.updateProduto(produtoId, "Novo Nome", 40.0));

        // Verifying repository save method was not called
        verify(produtoRepository, never()).save(any());
    }
}
