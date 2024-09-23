package com.outsera.outsera_movie_challenge.service;

import com.outsera.outsera_movie_challenge.dto.MovieDto;
import com.outsera.outsera_movie_challenge.dto.YearWinnerDto;
import com.outsera.outsera_movie_challenge.dto.YearWinnerMovieDto;
import com.outsera.outsera_movie_challenge.entity.Movie;
import com.outsera.outsera_movie_challenge.exception.BadRequestException;
import com.outsera.outsera_movie_challenge.exception.ResourceNotFoundException;
import com.outsera.outsera_movie_challenge.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MovieService {

	@Value("classpath:static/movielist.csv" )
	private Resource resource;

	final CsvService csvService;
	private final MovieRepository movieRepository;
	private final StudioService studioService;
	private final ProducerService producerService;

	public Long count() {
		return movieRepository.count();
	}

	public void saveFromCsv() {
		Iterable<CSVRecord> records = null;
		try (InputStream inputStream = resource.getInputStream()) {
			records = parseCSV(inputStream);

			records.forEach(record -> {
				Movie movie = save(record);

				Optional.ofNullable(movie).ifPresent(m -> {
					String studios = record.get("studios" );
					studioService.saveStudios(m, studios);

					String producers = record.get("producers" );
					producerService.saveProducers(m, producers);
				});
			});
		} catch (IOException e) {
			log.error("IOException: {}", e.getMessage());
		} catch (Exception e) {
			log.error("General exception: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	private Movie save(CSVRecord record) throws RuntimeException {
		Movie movie = new Movie(Integer.valueOf(record.get("year" )),
				record.get("title" ),
				record.get("winner" ));
		try {
			movieRepository.save(movie);
		} catch (Exception e) {
			log.error("Error saving new Movie: {}", e.getMessage());
		} finally {
			return movie;
		}
	}

	private Iterable<CSVRecord> parseCSV(InputStream inputStream) throws IOException {
		return csvService.parseCSV(inputStream);
	}

	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	public List<Movie> getMoviesFromAYear(Integer year) {
		return movieRepository.findByYear(year);
	}

	public List<MovieDto> getMoviesByYear(Integer year) {
		List<Movie> movies = movieRepository.findByYear(year);

		if (movies == null || movies.isEmpty()) {
			return new ArrayList<>();
		}

		List<MovieDto> moviesDto = new ArrayList<>();
		for (Movie m : movies) {
			moviesDto.add(new MovieDto(m));
		}

		return moviesDto;
	}

	public YearWinnerDto getYearsWithMoreThanOneWinners() {
		List<YearWinnerMovieDto> years = movieRepository.findYearsWithMoreThanOneWinner();
		if (years == null || years.isEmpty()) {
			return new YearWinnerDto();
		}
		return new YearWinnerDto(years);
	}

	public void remove(Long id) {
		Optional<Movie> optional = movieRepository.findById(id);

		if (!optional.isPresent()) {
			throw new ResourceNotFoundException();
		}

		Movie movie = optional.get();
		if (movie.isWinner()) {
			throw new BadRequestException();
		}

		movieRepository.delete(movie);
	}

}
