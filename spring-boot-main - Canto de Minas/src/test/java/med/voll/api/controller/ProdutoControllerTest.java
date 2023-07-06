package med.voll.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import canto.minas.api.domain.produto.DadosCadastroProduto;
import canto.minas.api.domain.produto.DadosDetalhamentoProduto;
import canto.minas.api.domain.produto.Produto;
import canto.minas.api.domain.produto.ProdutoRepository;
//import canto.minas.api.domain.repositor.DadosCadastroRepositor;
//import canto.minas.api.domain.repositor.DadosDetalhamentoRepositor;
//import canto.minas.api.domain.repositor.Repositor;
//import canto.minas.api.domain.repositor.RepositorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import java.util.Collections;
//import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private ProdutoRepository repository;

        @Test
        @DisplayName("Deveria cadastrar um novo Produto")
        void cadastrar() throws Exception {
                // Dados de cadastro do Produto
                DadosCadastroProduto dadosCadastro = new DadosCadastroProduto("Nome do Produto", "Descrição do Produto",
                                "link.com/foto", "1234567890");

                // Converter objeto em JSON
                String jsonCadastro = objectMapper.writeValueAsString(dadosCadastro);

                // Enviar requisição POST simulada para /produtos
                MvcResult result = mockMvc.perform(post("/produto")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonCadastro))
                                .andExpect(status().isCreated())
                                .andReturn();

                // Verificar a resposta
                String responseContent = result.getResponse().getContentAsString();
                DadosDetalhamentoProduto detalhamentoProduto = objectMapper.readValue(responseContent,
                                DadosDetalhamentoProduto.class);

                // Verificar se o Produto foi salvo corretamente no banco de dados
                Produto produtoSalvo = repository.findById(detalhamentoProduto.id()).orElse(null);
                assertNotNull(produtoSalvo);
                assertEquals(dadosCadastro.nome(), produtoSalvo.getNome());
                assertEquals(dadosCadastro.descricao(), produtoSalvo.getDescricao());
                assertEquals(dadosCadastro.codProd(), produtoSalvo.getCodProd());
                // Verificar se a foto foi carregada corretamente
                assertNotNull(produtoSalvo.getFoto());
        }
}
