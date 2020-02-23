package com.crypt_currency.mybtc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MyBtcApplicationTests {

	@LocalServerPort
	int port;
	
	@Autowired
	WebApplicationContext wc;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}
	
	@Test
	public void test_AllQuotes() throws Exception {
		
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/currency/all"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
		
		assertThat(response
				.getContentAsString()
				.contains("Bitcoin"));
	}

	
	@Test
	public void test_validSymbol() throws Exception {
		
		
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/currency/BTCUSD"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
				
		assertThat(response
				.getContentAsString()
				.contains("Bitcoin"));
	}
	
	@Test
	public void test_InvalidSymbol() throws Exception {
		
		
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/currency/invalid"))
				.andExpect(status().is4xxClientError())
				.andReturn()
				.getResponse();
				
		assertThat(response
				.getContentAsString()
				.contains("Currency symbol must be 6 character length."));
	}
	
}
