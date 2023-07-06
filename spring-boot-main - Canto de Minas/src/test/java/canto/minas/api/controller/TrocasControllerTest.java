package canto.minas.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

//import canto.minas.api.domain.produto.DadosCadastroProduto;
//import canto.minas.api.domain.produto.DadosDetalhamentoProduto;
//import canto.minas.api.domain.produto.Produto;
//import canto.minas.api.domain.produto.ProdutoRepository;
//import canto.minas.api.domain.repositor.DadosCadastroRepositor;
//import canto.minas.api.domain.repositor.DadosDetalhamentoRepositor;
//import canto.minas.api.domain.repositor.Repositor;
//import canto.minas.api.domain.repositor.RepositorRepository;
import canto.minas.api.domain.trocas.DadosCadastroTroca;
import canto.minas.api.domain.trocas.DadosDetalhamentoTroca;
import canto.minas.api.domain.trocas.Troca;
import canto.minas.api.domain.trocas.TrocaRepository;

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
class TrocasControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private TrocaRepository repository;

        @Test
        @DisplayName("Deveria cadastrar uma nova Troca")
        void cadastrar() throws Exception {
                // Dados de cadastro da Troca

                DadosCadastroTroca dadosCadastro = new DadosCadastroTroca(Long.valueOf(1),
                                Long.valueOf(2),
                                Long.valueOf(3),
                                "lote123",
                                "2023-07-06",
                                "Descrição da troca",
                                "5",
                                "2023-07-01",
                                "2023-07-31",
                                "Motivo da troca");

                // Converter objeto em JSON
                String jsonCadastro = objectMapper.writeValueAsString(dadosCadastro);

                // Enviar requisição POST simulada para /trocas
                MvcResult result = mockMvc.perform(post("/trocas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonCadastro))
                                .andExpect(status().isCreated())
                                .andReturn();

                // Verificar a resposta
                String responseContent = result.getResponse().getContentAsString();
                DadosDetalhamentoTroca detalhamentoTroca = objectMapper.readValue(responseContent,
                                DadosDetalhamentoTroca.class);

                // Verificar se a Troca foi salva corretamente no banco de dados
                Troca trocaSalva = repository.findById(detalhamentoTroca.id()).orElse(null);
                assertNotNull(trocaSalva);
                assertEquals(dadosCadastro.idLoja(), trocaSalva.getIdLoja());
                assertEquals(dadosCadastro.idRepositor(), trocaSalva.getIdRepositor());
                assertEquals(dadosCadastro.idProduto(), trocaSalva.getIdProduto());
                assertEquals(dadosCadastro.lote(), trocaSalva.getLote());
                assertEquals(dadosCadastro.dataTroca(), trocaSalva.getDataTroca());
                assertEquals(dadosCadastro.descricao(), trocaSalva.getDescricao());
                assertEquals(dadosCadastro.quantidade(), trocaSalva.getQuantidade());
                assertEquals(dadosCadastro.dataFabricacao(), trocaSalva.getDataFabricacao());
                assertEquals(dadosCadastro.dataVencimento(), trocaSalva.getDataVencimento());
                assertEquals(dadosCadastro.motivoTroca(), trocaSalva.getMotivoTroca());
        }
}
