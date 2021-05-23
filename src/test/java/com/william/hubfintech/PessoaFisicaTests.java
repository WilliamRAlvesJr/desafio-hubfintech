package com.william.hubfintech;

import com.william.hubfintech.domain.dto.PessoaFisicaDTO;
import com.william.hubfintech.service.PessoaFisicaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaFisicaTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	@Test
	void create() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.post("/v1/public/pessoas-fisicas/criar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					"{\"cpf\": \"111.111.111-11\",\"dataNascimento\": \"1970-12-28\",\"nomeCompleto\": \"João da Silva\"}"
				))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void select() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.post("/v1/public/pessoas-fisicas/criar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"cpf\": \"111.111.111-12\",\"dataNascimento\": \"1970-12-28\",\"nomeCompleto\": \"João da Silva\"}"
				))
				.andExpect(status().isOk())
				.andReturn();

		assertFalse(pessoaFisicaService.selectAll().isEmpty());
	}

	@Test
	void findByCpf() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.post("/v1/public/pessoas-fisicas/criar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"cpf\": \"111.111.111-13\",\"dataNascimento\": \"1970-12-28\",\"nomeCompleto\": \"João da Silva\"}"
				))
				.andExpect(status().isOk())
				.andReturn();

		PessoaFisicaDTO savedDto = pessoaFisicaService.findByCpf("111.111.111-13");
		assertThat(savedDto, is(not(nullValue())));
	}

}
