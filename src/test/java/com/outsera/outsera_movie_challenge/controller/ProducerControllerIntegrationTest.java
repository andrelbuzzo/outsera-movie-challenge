package com.outsera.outsera_movie_challenge.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@WebAppConfiguration
public class ProducerControllerIntegrationTest {

	private MockMvc mockMvc;

	@Autowired
	private ProducerController producerController;

	@Autowired
	protected WebApplicationContext wac;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.producerController).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getGreatestWinnersTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/producer/interval-prizes").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.min.*.producer", hasItem(is("Joel Silver"))))
				.andExpect(jsonPath("$.max.*.producer", hasItem(is("Matthew Vaughn"))));
	}

}