package com.outsera.outsera_movie_challenge.service;

import com.outsera.outsera_movie_challenge.dto.MovieDto;
import com.outsera.outsera_movie_challenge.model.Movie;
import com.outsera.outsera_movie_challenge.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MovieService {

	@Value("classpath:static/movielist.csv")
	private Resource resource;

	final CsvService<Movie, MovieDto> csvService;
	private final MovieRepository movieRepository;

	public Long count() {
		return movieRepository.count();
	}

	public Integer saveFromCsv() throws IOException {
		List<Movie> movies = null;
		try (InputStream inputStream = resource.getInputStream()) {
			movies = parseCSV(inputStream);
//			movieRepository.deleteAll();
			movieRepository.saveAll(movies);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return movies.size();
	}

	private List<Movie> parseCSV(InputStream inputStream) throws IOException {
		return csvService.parseCSV(inputStream, MovieDto.class);
	}

	public void populateMovies() throws IOException {
		try (InputStream inputStream = resource.getInputStream()) {
			List<Movie> movies = parseCSV(inputStream);
			movies.forEach(m -> {
				log.info(m);
			});
		}
	}

}
