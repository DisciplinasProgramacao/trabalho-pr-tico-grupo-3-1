import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo3.trabalhopratico.controllers.ProdutoController;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
public class ProdutoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoService produtoService;

    private Produto produto;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto Teste", 10.0, "Comida");
    }

    @Test
    public void testCriarProduto() throws Exception {
        // Convertendo o Produto para JSON
        String produtoJson = objectMapper.writeValueAsString(produto);

        // Configurando o comportamento do serviço para métodos que retornam void
        doNothing().when(produtoService).addNewProduto(any(Produto.class));

        // Realizando a requisição POST
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoJson))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));

        // Verificando se o método do serviço foi chamado
        verify(produtoService, times(1)).addNewProduto(any(Produto.class));
    }
}
