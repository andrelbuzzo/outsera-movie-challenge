package com.outsera.outsera_movie_challenge.controller;

import com.outsera.outsera_movie_challenge.dto.MovieDto;
import com.outsera.outsera_movie_challenge.dto.YearWinnerDto;
import com.outsera.outsera_movie_challenge.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/movie" )
public class MovieController {
	private final MovieService movieService;

	@GetMapping("/{year}" )
	@Operation(summary = "Get movies by the provided year")
	public ResponseEntity<List<MovieDto>> getMovies(@PathVariable(name = "year" ) Integer year) {
		List<MovieDto> movies = movieService.getMoviesByYear(year);

		HttpStatus status = HttpStatus.OK;
		if (movies.isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<>(movies, status);
	}

	/**
	 * @return {@link YearWinnerDto}
	 */
	@GetMapping("/years" )
	@Operation(summary = "Get years with more than one winner")
	public ResponseEntity<YearWinnerDto> getYearsWithMoreThanOneWinners() {
		YearWinnerDto dto = movieService.getYearsWithMoreThanOneWinners();

		HttpStatus status = HttpStatus.OK;
		if (dto.getYears().isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<YearWinnerDto>(dto, status);
	}

	@DeleteMapping("/{id}" )
	@Operation(summary = "Remove movie by the provided id")
	public ResponseEntity<Void> removeMovie(@PathVariable(name = "id" ) Long id) {
		movieService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
