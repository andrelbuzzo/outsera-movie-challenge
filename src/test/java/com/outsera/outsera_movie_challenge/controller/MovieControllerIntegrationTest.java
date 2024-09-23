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
public class MovieControllerIntegrationTest {

	private MockMvc mockMvc;

	@Autowired
	private MovieController movieController;

	@Autowired
	protected WebApplicationContext wac;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.movieController).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getMoviesByYearTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movie/2017").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.*.title",
						hasItem(is("The Emoji Movie"))));
	}

	@Test
	public void getYearsWithMoreThanOneWinnersTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movie/years").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.*.*.year", hasItem(is(1986))))
				.andExpect(jsonPath("$.*.*.winnerCount", hasItem(is(2))));
	}

	@Test
	public void removeTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/movie/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void removeBadRequestTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/movie/26").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void removeNoContentTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/movie/300").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}