package med.voll.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import canto.minas.api.domain.repositor.DadosCadastroRepositor;
import canto.minas.api.domain.repositor.DadosDetalhamentoRepositor;
import canto.minas.api.domain.repositor.Repositor;
import canto.minas.api.domain.repositor.RepositorRepository;
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
class RepositorControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private RepositorRepository repository;

        @Test
        @DisplayName("Deveria cadastrar um novo Repositor")
        void cadastrar() throws Exception {
                // Dados de cadastro do Repositor
                DadosCadastroRepositor dadosCadastro = new DadosCadastroRepositor(
                                "Nome do Repositor", "1234567890", "repositor@example.com", "999999999");

                // Converter objeto em JSON
                String jsonCadastro = objectMapper.writeValueAsString(dadosCadastro);

                // Enviar requisição POST simulada para /repositor
                MvcResult result = mockMvc.perform(post("/repositor")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonCadastro))
                                .andExpect(status().isCreated())
                                .andReturn();

                // Verificar a resposta
                String responseContent = result.getResponse().getContentAsString();
                DadosDetalhamentoRepositor detalhamentoRepositor = objectMapper.readValue(responseContent,
                                DadosDetalhamentoRepositor.class);

                // Verificar se o Repositor foi salvo corretamente no banco de dados
                Repositor repositorSalvo = repository.findById(detalhamentoRepositor.id()).orElse(null);
                assertNotNull(repositorSalvo);
                assertEquals(dadosCadastro.nome(), repositorSalvo.getNome());
                assertEquals(dadosCadastro.cpf(), repositorSalvo.getCpf());
                assertEquals(dadosCadastro.email(), repositorSalvo.getEmail());
                assertEquals(dadosCadastro.telefone(), repositorSalvo.getTelefone());
        }
}