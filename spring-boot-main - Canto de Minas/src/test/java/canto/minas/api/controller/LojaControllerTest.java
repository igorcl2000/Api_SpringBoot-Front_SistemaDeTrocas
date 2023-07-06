package canto.minas.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import canto.minas.api.domain.endereco.DadosEndereco;
//import canto.minas.api.domain.endereco.Endereco;
import canto.minas.api.domain.loja.DadosCadastroLoja;
import canto.minas.api.domain.loja.DadosDetalhamentoLoja;
import canto.minas.api.domain.loja.Loja;
import canto.minas.api.domain.loja.LojaRepository;

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
class LojaControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private LojaRepository repository;

        @Test
        @DisplayName("Deveria cadastrar uma nova Loja")
        void cadastrar() throws Exception {
                // Dados de cadastro da Loja

                DadosEndereco endereco = new DadosEndereco(
                                "Logradouro",
                                "Bairro",
                                "00000000",
                                "Cidade",
                                "UF",
                                "complemento",
                                "7");

                DadosCadastroLoja dadosCadastro = new DadosCadastroLoja(
                                "Razão Social da Loja",
                                "Nome Fantasia da Loja",
                                "1234567890",
                                "9999999999",
                                "Responsável da Loja", endereco);

                // Converter objeto em JSON
                String jsonCadastro = objectMapper.writeValueAsString(dadosCadastro);

                // Enviar requisição POST simulada para /loja
                MvcResult result = mockMvc.perform(post("/loja")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonCadastro))
                                .andExpect(status().isCreated())
                                .andReturn();

                // Verificar a resposta
                String responseContent = result.getResponse().getContentAsString();
                DadosDetalhamentoLoja detalhamentoLoja = objectMapper.readValue(responseContent,
                                DadosDetalhamentoLoja.class);

                // Verificar se a Loja foi salva corretamente no banco de dados
                Loja lojaSalva = repository.findById(detalhamentoLoja.id()).orElse(null);
                assertNotNull(lojaSalva);
                assertEquals(dadosCadastro.rasaoSocial(), lojaSalva.getRasaoSocial());
                assertEquals(dadosCadastro.nomeFantasia(), lojaSalva.getNomeFantasia());
                assertEquals(dadosCadastro.cnpj(), lojaSalva.getCnpj());
                assertEquals(dadosCadastro.telefone(), lojaSalva.getTelefone());
                assertEquals(dadosCadastro.responsavel(), lojaSalva.getResponsavel());
                assertEquals(dadosCadastro.endereco().logradouro(), lojaSalva.getEndereco().getLogradouro());
                assertEquals(dadosCadastro.endereco().bairro(), lojaSalva.getEndereco().getBairro());
                assertEquals(dadosCadastro.endereco().cep(), lojaSalva.getEndereco().getCep());
                assertEquals(dadosCadastro.endereco().numero(), lojaSalva.getEndereco().getNumero());
                assertEquals(dadosCadastro.endereco().complemento(), lojaSalva.getEndereco().getComplemento());
                assertEquals(dadosCadastro.endereco().cidade(), lojaSalva.getEndereco().getCidade());
                assertEquals(dadosCadastro.endereco().uf(), lojaSalva.getEndereco().getUf());
        }
}