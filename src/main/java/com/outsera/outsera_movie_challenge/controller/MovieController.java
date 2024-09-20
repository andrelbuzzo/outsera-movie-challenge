package com.outsera.outsera_movie_challenge.controller;

import com.outsera.outsera_movie_challenge.dto.BaseResponse;
import com.outsera.outsera_movie_challenge.dto.MovieResponse;
import com.outsera.outsera_movie_challenge.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MovieController {
	private final MovieService movieService;

	/*
	- Obter o produtor com maior intervalo entre dois prêmios consecutivos,
	- e o que obteve dois prêmios mais rápido
	* */

	@GetMapping("/v1/movies")
	public ResponseEntity<MovieResponse> getA(){
		try {
			List<BaseResponse> min = movieService.getAwardInterval("min");
			log.info("min: {}", min);
			List<BaseResponse> max = movieService.getAwardInterval("max");
//			log.info("max: {}", max);
			MovieResponse movieResponse = new MovieResponse(min, max);
			log.info("movieResponse: {}", movieResponse);

			return ResponseEntity.ok(movieResponse);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity("Error retrieving movie data.", HttpStatus.BAD_REQUEST);
		}
	}

}
