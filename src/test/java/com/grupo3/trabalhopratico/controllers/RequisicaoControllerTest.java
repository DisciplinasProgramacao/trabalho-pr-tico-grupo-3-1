import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo3.trabalhopratico.controllers.RequisicaoController;
import com.grupo3.trabalhopratico.models.Cliente;
import com.grupo3.trabalhopratico.models.Produto;
import com.grupo3.trabalhopratico.models.Requisicao;
import com.grupo3.trabalhopratico.services.RequisicaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequisicaoController.class)
public class RequisicaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RequisicaoService requisicaoService;

    @Test
    public void testRegisterNewRequisicao() throws Exception {
        // Criando um Cliente para a Requisição
        Cliente cliente = new Cliente("Cliente Teste");

        // Criando a Requisição
        Requisicao requisicao = new Requisicao(null, "Cliente Teste", 4, true, false, Collections.emptyList(), cliente);

        // Convertendo a Requisicao para JSON
        String requisicaoJson = objectMapper.writeValueAsString(requisicao);

        // Configurando o comportamento do serviço para métodos void
        doNothing().when(requisicaoService).addNewRequisicao(any(Requisicao.class));

        // Realizando a requisição POST
        mockMvc.perform(post("/requisicoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requisicaoJson))
                .andExpect(status().isCreated())
                .andExpect(content().string("Requisição criada com sucesso"));

        // Verificando se o método do serviço foi chamado
        verify(requisicaoService, times(1)).addNewRequisicao(any(Requisicao.class));
    }
}
